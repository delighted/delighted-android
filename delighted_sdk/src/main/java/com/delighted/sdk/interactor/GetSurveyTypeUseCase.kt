package com.delighted.sdk.interactor

import com.delighted.sdk.domain.response.SurveyTypeIdentifier
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieve the type of survey, if it exists
 */
internal class GetSurveyTypeUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, SurveyTypeIdentifier> {
    override fun execute(params: Unit): SurveyTypeIdentifier {
        val surveyType = surveyFlowRepo
            .survey
            ?.surveyResponse
            ?.surveyTypeResponse
        requireNotNull(surveyType) { "Survey type was null. Is survey set?" }
        return surveyType.id
    }
}
