package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SurveyRequestResponse(
    @Json(name = "token") val token: String,
    @Json(name = "survey") val surveyResponse: SurveyResponse,
    @Json(name = "thank_you") val thankYou: ThankYouResponse,
)
