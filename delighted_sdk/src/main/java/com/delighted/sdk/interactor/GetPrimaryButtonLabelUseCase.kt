package com.delighted.sdk.interactor

import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the appropriate text for the primary button
 */
internal class GetPrimaryButtonLabelUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, String> {
    override fun execute(params: Unit): String =
        surveyFlowRepo.survey?.let { survey ->
            val hasAdditionalQuestions: Boolean =
                survey.surveyResponse.surveyTemplateResponse.additionalQuestions?.isEmpty() == false
            val nextText = survey
                .surveyResponse
                .surveyConfigurationResponse
                .nextText
            val submitText = survey
                .surveyResponse
                .surveyConfigurationResponse
                .submitText

            if (hasAdditionalQuestions) nextText else submitText
        }.orEmpty()
}
