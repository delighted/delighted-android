package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThankYouResponse(
    @Json(name = "text") val text: String,
    @Json(name = "auto_close_delay") val autoCloseDelay: Int? = null,
    @Json(name = "groups") val groups: Map<String, Map<String, String>>? = null,
)
