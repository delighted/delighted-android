package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PusherAuthResponse(
    @Json(name = "auth") val auth: String,
)
