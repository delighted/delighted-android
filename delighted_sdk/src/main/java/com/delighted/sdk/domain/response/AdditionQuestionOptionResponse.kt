package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionQuestionOptionResponse(
    @Json(name = "id") val id: String,
    @Json(name = "text") val text: String
)
