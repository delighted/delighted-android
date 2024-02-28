package com.delighted.sdk.interactor

import com.delighted.sdk.domain.SurveyUserGroup
import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the user's current user group based on the user's score to the First Question.
 * Null or "no group" is a valid state.
 */
internal class GetGroupForUserScoreUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, SurveyUserGroup?> {
    override fun execute(params: Unit): SurveyUserGroup? {
        val surveyTypeResponse =
            surveyFlowRepo.survey?.surveyResponse?.surveyTypeResponse
        val currentScore = surveyFlowRepo.getFirstQuestionAnswerData().score

        val userGroups =
            surveyTypeResponse?.let { fromResponse(surveyTypeResponse = it).surveyUserGroups }
        val groupName = userGroups?.find { currentScore in it.scoreMin..it.scoreMax }?.name

        // it is valid that a user's score does not put them into a group
        return userGroups?.find { it.name == groupName }
    }
}
