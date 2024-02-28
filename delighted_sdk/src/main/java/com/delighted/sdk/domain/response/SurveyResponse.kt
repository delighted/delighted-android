package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class SurveyResponse(
    @Json(name = "type") val surveyTypeResponse: SurveyTypeResponse,
    @Json(name = "configuration") val surveyConfigurationResponse: SurveyConfigurationResponse,
    @Json(name = "template") val surveyTemplateResponse: SurveyTemplateResponse
)
