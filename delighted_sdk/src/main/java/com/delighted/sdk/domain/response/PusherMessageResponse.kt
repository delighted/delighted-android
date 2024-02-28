package com.delighted.sdk.domain.response

import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.AUTH
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.CHANNEL
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.CLIENT_TYPING
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_CONN_ESTABLISHED
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_ERROR
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_SUBSCRIBE
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.PUSHER_SUBSCRIPTION_SUCCEEDED
import com.delighted.sdk.domain.response.PusherMessageResponse.Companion.SURVEY_REQUEST_TOKEN
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class PusherMessageResponse(
    @Json(name = "event") val event: String,
    @Json(name = "data") val data: Map<String, String>,
    @Json(name = "channel") val channel: String? = null
) {
    companion object {
        const val SOCKET_ID = "socket_id"
        const val AUTH = "auth"
        const val CHANNEL = "channel"
        const val SURVEY_REQUEST_TOKEN = "survey_request_token"

        const val PUSHER_SUBSCRIBE = "pusher:subscribe"
        const val PUSHER_CONN_ESTABLISHED = "pusher:connection_established"
        const val PUSHER_ERROR = "pusher:error"
        const val CLIENT_TYPING = "client-typing"
        const val PUSHER_SUBSCRIPTION_SUCCEEDED = "pusher_internal:subscription_succeeded"

        fun fromPusherMessage(pusherMessage: PusherMessage) = PusherMessageResponse(
            event = pusherMessage.event,
            channel = pusherMessage.channel,
            data = pusherMessage.data
        )
    }
}

sealed class PusherMessage(
    val event: String,
    val channel: String? = null,
    val data: Map<String, String>,
) {

    data class Subscribe(val authToken: String, val channelName: String) :
        PusherMessage(
            event = PUSHER_SUBSCRIBE,
            data = mapOf(
                AUTH to authToken,
                CHANNEL to channelName
            )
        )

    data class ClientTyping(val channelName: String, val surveyRequestToken: String) :
        PusherMessage(
            event = CLIENT_TYPING,
            channel = channelName,
            data = mapOf(
                SURVEY_REQUEST_TOKEN to surveyRequestToken
            )
        )

    class ConnectionEstablished(channelName: String?, data: Map<String, String>) :
        PusherMessage(
            event = PUSHER_CONN_ESTABLISHED,
            channel = channelName,
            data = data,
        )

    class Error(channelName: String?, data: Map<String, String>) :
        PusherMessage(
            event = PUSHER_ERROR,
            channel = channelName,
            data = data
        )

    class SubscriptionSucceeded(data: Map<String, String>) :
        PusherMessage(
            event = PUSHER_SUBSCRIPTION_SUCCEEDED,
            data = data
        )

    class Unknown(event: String, channelName: String?, data: Map<String, String>) : PusherMessage(
        event = event,
        channel = channelName,
        data = data
    )
}
