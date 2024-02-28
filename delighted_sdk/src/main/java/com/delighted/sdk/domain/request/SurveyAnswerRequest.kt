package com.delighted.sdk.domain.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurveyAnswerRequest(
    @Json(name = "survey_request_token") val surveyRequestToken: String,
    @Json(name = "score") val score: Int? = null,
    @Json(name = "comment") val comment: String? = null,
    @Json(name = "additional_questions") val additionalQuestionAnswers: List<AdditionalQuestionAnswer>? = null
)
