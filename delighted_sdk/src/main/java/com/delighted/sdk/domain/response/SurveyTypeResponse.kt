package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurveyTypeResponse(
    @Json(name = "id") val id: SurveyTypeIdentifier,
    @Json(name = "groups") val groups: Map<String, Map<String, String>>,
)
