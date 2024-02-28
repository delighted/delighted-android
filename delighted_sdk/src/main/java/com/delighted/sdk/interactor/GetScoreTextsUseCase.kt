package com.delighted.sdk.interactor

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the "score text" for the survey as a map of score values to display strings,
 * returns null if it does not exist.
 */
internal class GetScoreTextsUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, Map<Int, String>?> {
    override fun execute(params: Unit): Map<Int, String>? =
        surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.fromResponse()
            ?.scoreText
}
