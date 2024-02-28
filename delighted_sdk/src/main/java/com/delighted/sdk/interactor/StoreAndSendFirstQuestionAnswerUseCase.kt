package com.delighted.sdk.interactor

import com.delighted.sdk.interactor.StoreAndSendFirstQuestionAnswerUseCase.Params
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Stores answer values for the first question in data layer and
 * also sends these values to backend
 */
internal class StoreAndSendFirstQuestionAnswerUseCase @Inject constructor(
    private val sendSurveyAnswerUseCase: SendSurveyAnswerUseCase,
    private val surveyFlowRepo: SurveyFlowRepository,
) :
    SuspendableUseCase<Params, Unit> {
    override suspend fun execute(params: Params) {
        params.score?.let { surveyFlowRepo.storeFirstQuestionScore(score = it) }
        params.comment?.let { surveyFlowRepo.storeFirstQuestionComment(comment = it) }
        sendSurveyAnswerUseCase()
    }

    data class Params(
        val score: Int? = null,
        val comment: String? = null
    )
}
