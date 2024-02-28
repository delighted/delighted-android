package com.delighted.sdk.domain

import com.delighted.sdk.domain.response.AdditionalQuestionResponse

/**
 * Survey template object, with maps guaranteed to be sorted
 */
data class SurveyTemplate(
    val questionText: String,
    val commentPrompts: Map<Int, String>?,
    val scoreText: Map<Int, String>,
    val additionalQuestions: List<AdditionalQuestionResponse>?,
)
