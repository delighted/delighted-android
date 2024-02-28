package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThankYouGroupResponse(
    @Json(name = "message") val message: String,
    @Json(name = "link_text") val linkText: String,
    @Json(name = "link_url") val linkUrl: String,
)
