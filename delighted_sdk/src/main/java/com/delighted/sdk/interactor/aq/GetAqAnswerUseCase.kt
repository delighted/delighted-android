package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.FREE_RESPONSE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SCALE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_MANY
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_ONE
import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerFreeForm
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerScale
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerSelect
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqAnswerUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getAdditionalQuestionUseCase: GetAdditionalQuestionUseCase,
) :
    UseCase<String, AnswerParams?> {
    override fun execute(params: String): AnswerParams? {
        val storedAnswer =
            surveyFlowRepo.getAdditionalQuestionAnswer(questionId = params) ?: return null
        return when (getAdditionalQuestionUseCase(params = params)?.type) {
            FREE_RESPONSE -> AnswerFreeForm(content = storedAnswer, questionId = params)
            SCALE -> AnswerScale(value = storedAnswer.toInt(), questionId = params)
            SELECT_ONE -> AnswerSelect(list = listOf(storedAnswer), questionId = params)
            SELECT_MANY -> AnswerSelect(
                list = storedAnswer.filter { !it.isWhitespace() }
                    .split(","),
                questionId = params
            )
            null -> null
        }
    }
}
