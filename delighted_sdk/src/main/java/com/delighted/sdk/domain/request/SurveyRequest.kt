package com.delighted.sdk.domain.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurveyRequest(
    @Json(name = "person") val person: Person?,
    @Json(name = "properties") val properties: Map<String, String>?,
    @Json(name = "token") val token: String?,
    @Json(name = "test_mode") val testMode: Boolean? = false
)
