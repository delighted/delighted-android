package com.delighted.sdk.network

import com.delighted.sdk.AppDispatchers
import com.delighted.sdk.core.scope
import com.delighted.sdk.domain.request.PusherAuthRequest
import com.delighted.sdk.domain.response.PusherMessage.ClientTyping
import com.delighted.sdk.domain.response.PusherMessage.ConnectionEstablished
import com.delighted.sdk.domain.response.PusherMessage.Error
import com.delighted.sdk.domain.response.PusherMessage.Subscribe
import com.delighted.sdk.domain.response.PusherMessage.SubscriptionSucceeded
import com.delighted.sdk.domain.response.PusherMessage.Unknown
import com.delighted.sdk.domain.response.PusherMessageResponse
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.SOCKET_ID
import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ConnectingPusher
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.NotActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URI
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PusherService @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val networkFetch: NetworkFetch,
    private val jsonHandler: JsonHandler,
    private val dispatchers: AppDispatchers,
) {

    private var notificationsEnabled = false
    private var notificationsCount = 0
    private var channelName: String? = null
    private var client: PusherWebSocketClient? = null
    private var lastSent = Instant.now()
    private var messageCollectionJob: Job? = null
    private var pusherConnectionJob: Job = createPusherConnectionJob()

    fun sendTypingNotification() {
        dispatchers.io.scope().launch {
            if (pusherConnectionJob.isCompleted) {
                pusherConnectionJob = createPusherConnectionJob()
                delay(1000) // let pusher re-connect catch up
            }
            if (notificationsEnabled) {
                if (Instant.now().minusSeconds(2).isBefore(lastSent)) return@launch
                val cName = channelName ?: return@launch
                val token = surveyFlowRepo.getSurveyToken().getOrNull() ?: return@launch

                val message = PusherMessageResponse.fromPusherMessage(
                    pusherMessage = ClientTyping(
                        channelName = cName,
                        surveyRequestToken = token,
                    )
                )
                val jsonPayload =
                    jsonHandler.moshi.adapter(PusherMessageResponse::class.java).toJson(message)
                if (client?.isOpen == true) client?.send(jsonPayload)
                lastSent = Instant.now()
                if (++notificationsCount == MAX_MESSAGES) notificationsEnabled = false
            }
        }
    }

    private fun connectToPusher(
        pusherWebSocketUrl: String,
        channelName: String
    ) {
        client = PusherWebSocketClient(
            serverURI = URI.create(pusherWebSocketUrl),
            jsonHandler = jsonHandler,
        ).also {
            pusherSetup(channelName = channelName, client = it)
            it.connect()
        }
    }

    private suspend fun getAuthToken(
        pusherMessage: ConnectionEstablished,
        channelName: String
    ): String? {
        val socketId = pusherMessage.data[SOCKET_ID]
        if (socketId == null) {
            Timber.d("No socket id present in $pusherMessage")
            surveyFlowRepo.connectedToPusher(connected = false)
            return null
        }

        val token = requestPusherAuthorizationToken(
            socketId = socketId,
            channelName = channelName
        )

        if (token == null) {
            Timber.d("Unable to get auth token")
            surveyFlowRepo.connectedToPusher(connected = false)
            return null
        }
        return token
    }

    private suspend fun requestPusherAuthorizationToken(
        socketId: String,
        channelName: String
    ): String? {
        val token =
            surveyFlowRepo.getSurveyToken().getOrNull() ?: return null
        val pusherAuthRequest = PusherAuthRequest(
            surveyRequestToken = token,
            socketId = socketId,
            channelName = channelName
        )
        // request pusher auth from Delighted API
        val pusherAuthReqJson: String =
            jsonHandler.encodePusherAuthRequest(pusherAuthRequest = pusherAuthRequest)
                .onFailure {
                    Timber.e(it)
                    return null
                }.getOrThrow()

        val pusherAuthResponseJson =
            networkFetch.requestPusherAuth(pusherAuthRequestJson = pusherAuthReqJson)
                .onFailure {
                    Timber.e(it)
                    return null
                }.getOrThrow()
        // then return auth token from response
        val pusherAuthResponse =
            jsonHandler.attemptDecodePusherAuthResponse(rawJson = pusherAuthResponseJson)
                .onFailure {
                    Timber.e(it)
                    return null
                }.getOrThrow()

        return pusherAuthResponse.auth
    }

    private fun subscribeToPusher(
        authToken: String,
        channelName: String,
        client: PusherWebSocketClient
    ) {
        val message = PusherMessageResponse.fromPusherMessage(
            pusherMessage = Subscribe(
                authToken = authToken,
                channelName = channelName
            )
        )
        val jsonPayload =
            jsonHandler.moshi.adapter(PusherMessageResponse::class.java).toJson(message)
        client.send(jsonPayload)
    }

    private fun pusherSetup(channelName: String, client: PusherWebSocketClient) {
        messageCollectionJob = dispatchers.io.scope().launch {
            client.pusherMessages.collect {
                when (it) {
                    is ClientTyping -> { /*these are only ever sent not received */
                    }
                    is ConnectionEstablished -> {
                        getAuthToken(
                            pusherMessage = it,
                            channelName = channelName
                        )?.let { authToken ->
                            subscribeToPusher(
                                client = client,
                                authToken = authToken,
                                channelName = channelName
                            )
                        }
                    }
                    is Error -> Timber.e("Error message from Pusher: $it")
                    is Subscribe -> { /*these are only ever sent not received */
                    }
                    is Unknown -> Timber.e("Unknown message from Pusher: $it")
                    is SubscriptionSucceeded -> {
                        surveyFlowRepo.connectedToPusher(connected = true)
                        notificationsEnabled = true
                        this@PusherService.channelName = channelName
                    }
                }
            }
        }
    }

    private fun createPusherConnectionJob() = dispatchers.io.scope().launch {
        surveyFlowRepo.surveyState.collect {
            when (it) {
                ConnectingPusher -> {
                    surveyFlowRepo.getPusher().onSuccess { pusherConfig ->
                        if (pusherConfig.enabled) {
                            connectToPusher(
                                pusherWebSocketUrl = pusherConfig.webSocketUrl!!,
                                channelName = pusherConfig.channelName!!
                            )
                        }
                    }
                }
                NotActive -> {
                    client?.close()
                    client = null
                    channelName = null
                    notificationsCount = 0
                    notificationsEnabled = false
                    messageCollectionJob?.cancel()
                    this.cancel()
                }
                else -> {}
            }
        }
    }

    companion object {
        const val MAX_MESSAGES = 2000
    }
}
