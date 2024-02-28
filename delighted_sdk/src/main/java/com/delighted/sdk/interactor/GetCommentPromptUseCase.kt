package com.delighted.sdk.interactor

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves a mapping of score values to comment strings, if it exists.
 * Example map returned:
 * 1 -> "Tell us why you chose 1"
 * 2 -> "Tell us why you chose 2"
 */
internal class GetCommentPromptUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, Map<Int, String>?> {
    override fun execute(params: Unit): Map<Int, String>? =
        surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.fromResponse()
            ?.commentPrompts
}
