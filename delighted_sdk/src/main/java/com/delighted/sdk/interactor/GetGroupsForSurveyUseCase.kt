package com.delighted.sdk.interactor

import com.delighted.sdk.domain.SurveyUserGroup
import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieve ALL of the potential user groups to which a user can belong based on their score
 */
internal class GetGroupsForSurveyUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, List<SurveyUserGroup>> {
    override fun execute(params: Unit): List<SurveyUserGroup> {
        val surveyTypeResponse =
            surveyFlowRepo.survey?.surveyResponse?.surveyTypeResponse
                ?: return emptyList()
        return fromResponse(surveyTypeResponse = surveyTypeResponse).surveyUserGroups
    }
}
