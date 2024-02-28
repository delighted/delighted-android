package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PusherConfigResponse(
    @Json(name = "web_socket_url") val webSocketUrl: String?,
    @Json(name = "channel_name") val channelName: String?,
    @Json(name = "enabled") val enabled: Boolean
)
