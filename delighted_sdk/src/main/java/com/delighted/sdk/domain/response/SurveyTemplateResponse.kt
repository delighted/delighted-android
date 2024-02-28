package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurveyTemplateResponse(
    @Json(name = "question_text") val questionText: String,
    @Json(name = "comment_prompts") val commentPrompts: Map<String, String>?,
    @Json(name = "score_text") val scoreText: Map<String, String>,
    @Json(name = "additional_questions") val additionalQuestions: List<AdditionalQuestionResponse>?,
)
