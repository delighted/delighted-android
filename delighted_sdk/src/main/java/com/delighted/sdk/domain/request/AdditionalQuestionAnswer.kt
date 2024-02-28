package com.delighted.sdk.domain.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalQuestionAnswer(
    @Json(name = "id") val id: String,
    @Json(name = "value") val value: String,
)
