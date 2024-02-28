package com.delighted.sdk.interactor

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the first question main text
 */
internal class FirstQuestionPromptUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, String?> {
    override fun execute(params: Unit): String? =
        surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.fromResponse()
            ?.questionText
}
