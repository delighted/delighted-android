package com.delighted.sdk.interactor

import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyInitRepository
import javax.inject.Inject

/**
 * Retrieve the survey theme settings from the survey, if it exists
 */
internal class GetSurveyThemeUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val surveyInitRepo: SurveyInitRepository,
) :
    UseCase<Unit, SurveyTheme> {
    override fun execute(params: Unit): SurveyTheme {
        surveyInitRepo.sdkInitParams.themeOverride?.let { surveyThemeOverride ->
            return surveyThemeOverride
        }
        val theme = surveyFlowRepo
            .survey
            ?.surveyResponse
            ?.surveyConfigurationResponse
            ?.theme
        requireNotNull(theme) { "Survey theme was null and there was no override. Is survey set?" }
        return fromResponse(surveyThemeResponse = theme)
    }
}
