package com.delighted.sdk.domain

import com.delighted.sdk.domain.response.SurveyTypeIdentifier

data class SurveyType(
    val id: SurveyTypeIdentifier,
    val surveyUserGroups: List<SurveyUserGroup>,
)
