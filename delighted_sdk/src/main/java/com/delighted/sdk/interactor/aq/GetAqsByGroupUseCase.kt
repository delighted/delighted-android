package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.response.AdditionalQuestionResponse
import com.delighted.sdk.interactor.GetGroupForUserScoreUseCase
import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqsByGroupUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getGroupForUserScoreUseCase: GetGroupForUserScoreUseCase
) :
    UseCase<Unit, List<AdditionalQuestionResponse>> {
    override fun execute(params: Unit): List<AdditionalQuestionResponse> {
        val surveyGroup = getGroupForUserScoreUseCase()
        return surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.additionalQuestions
            ?.filter {
                it.targetAudienceGroups.any { aqGroupName -> aqGroupName == surveyGroup?.name } ||
                    it.targetAudienceGroups.isEmpty() // no groups means everyone
            }
            ?: emptyList()
    }
}
