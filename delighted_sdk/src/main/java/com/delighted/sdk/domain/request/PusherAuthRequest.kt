package com.delighted.sdk.domain.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PusherAuthRequest(
    @Json(name = "survey_request_token") val surveyRequestToken: String,
    @Json(name = "socket_id") val socketId: String,
    @Json(name = "channel_name") val channelName: String,
)
