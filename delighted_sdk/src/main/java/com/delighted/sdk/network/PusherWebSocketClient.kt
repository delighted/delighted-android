package com.delighted.sdk.network

import com.delighted.sdk.domain.response.PusherMessage
import com.delighted.sdk.domain.response.PusherMessage.ConnectionEstablished
import com.delighted.sdk.domain.response.PusherMessage.Error
import com.delighted.sdk.domain.response.PusherMessage.SubscriptionSucceeded
import com.delighted.sdk.domain.response.PusherMessage.Unknown
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_CONN_ESTABLISHED
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_ERROR
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_SUBSCRIPTION_SUCCEEDED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import timber.log.Timber
import java.net.URI
import java.nio.ByteBuffer

class PusherWebSocketClient(
    serverURI: URI?,
    private val jsonHandler: JsonHandler,
) : WebSocketClient(serverURI) {

    private val _pusherMessages = MutableSharedFlow<PusherMessage>(replay = 1)
    val pusherMessages: Flow<PusherMessage> get() = _pusherMessages

    override fun onOpen(handshakedata: ServerHandshake) {
        Timber.d("new connection opened")
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        Timber.d("closed with exit code $code additional info: $reason")
    }

    override fun onMessage(message: String) {
        Timber.d("received message: $message")
        val msgResponse = jsonHandler.attemptDecodePusherMessage(message = message)
            .onFailure {
                Timber.e(it)
                return
            }.getOrThrow()
        val pusherMessage: PusherMessage = when (msgResponse.event) {
            PUSHER_ERROR -> Error(channelName = msgResponse.channel, data = msgResponse.data)
            PUSHER_CONN_ESTABLISHED -> ConnectionEstablished(
                channelName = msgResponse.channel,
                data = msgResponse.data
            )
            PUSHER_SUBSCRIPTION_SUCCEEDED -> SubscriptionSucceeded(data = msgResponse.data)
            else -> Unknown(
                event = msgResponse.event,
                channelName = msgResponse.channel,
                data = msgResponse.data
            )
        }
        _pusherMessages.tryEmit(pusherMessage)
    }

    override fun onMessage(message: ByteBuffer?) {
        Timber.d("received ByteBuffer")
    }

    override fun onError(ex: Exception) {
        Timber.e("an error occurred:$ex")
    }
}
