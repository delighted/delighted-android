package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.FREE_RESPONSE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SCALE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_MANY
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_ONE
import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqInstructionTextUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<String, String?> {
    override fun execute(params: String): String? {
        val question = surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.fromResponse()
            ?.additionalQuestions
            ?.find { it.id == params }

        val surveyConfig = surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyConfigurationResponse

        requireNotNull(question) { "No additional question with id $params" }
        requireNotNull(surveyConfig) { "No survey config response present" }
        return when (question.type) {
            FREE_RESPONSE -> null
            SCALE -> null
            SELECT_ONE -> surveyConfig.selectOneText
            SELECT_MANY -> surveyConfig.selectManyText
        }
    }
}
