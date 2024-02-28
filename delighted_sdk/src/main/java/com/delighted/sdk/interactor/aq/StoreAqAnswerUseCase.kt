package com.delighted.sdk.interactor.aq

import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerFreeForm
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerScale
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerSelect
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class StoreAqAnswerUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
) :
    UseCase<AnswerParams, Unit> {
    override fun execute(params: AnswerParams) {
        val data = when (params) {
            is AnswerFreeForm -> params.content
            is AnswerScale -> params.value.toString()
            is AnswerSelect -> params.list.joinToString()
        }

        surveyFlowRepo.storeAdditionalQuestionAnswer(
            questionId = params.questionId,
            answerData = data
        )
    }
}
