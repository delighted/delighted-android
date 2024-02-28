package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.response.AdditionalQuestionResponse
import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAdditionalQuestionUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<String, AdditionalQuestionResponse?> {
    override fun execute(params: String): AdditionalQuestionResponse? {
        val survey =
            requireNotNull(surveyFlowRepo.survey) { "No survey data stored in repo" }
        val hasAdditionalQuestions: Boolean =
            survey.surveyResponse.surveyTemplateResponse.additionalQuestions?.isEmpty() == false
        require(hasAdditionalQuestions) { "Cannot lookup additional questions in a survey with no additional questions" }
        return survey.surveyResponse.surveyTemplateResponse.additionalQuestions?.find { it.id == params }
    }
}
