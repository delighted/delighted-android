package com.delighted.sdk.interactor

import com.delighted.sdk.domain.request.SurveyAnswerRequest
import com.delighted.sdk.network.JsonHandler
import com.delighted.sdk.network.NetworkFetch
import com.delighted.sdk.repository.SurveyFlowRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Send the answer data for the first question to the backend. Return false if not successful
 */
internal class SendSurveyAnswerUseCase @Inject constructor(
    private val network: NetworkFetch,
    private val jsonHandler: JsonHandler,
    private val getTokenFromSurveyUseCase: GetTokenFromSurveyUseCase,
    private val surveyFlowRepo: SurveyFlowRepository,
) : SuspendableUseCase<Unit, Boolean> {
    override suspend fun execute(params: Unit): Boolean {
        val answerData = surveyFlowRepo.getFirstQuestionAnswerData()

        val surveyAnswerRequest = SurveyAnswerRequest(
            surveyRequestToken = getTokenFromSurveyUseCase(),
            score = answerData.score,
            comment = answerData.comment,
            additionalQuestionAnswers = emptyList()
        )
        val sendSurveyResponseJson =
            jsonHandler.encodeSurveyAnswer(surveyAnswerRequest = surveyAnswerRequest)
                .let { result ->
                    if (result.isFailure) {
                        Timber.e(result.exceptionOrNull())
                        return false
                    }
                    result.getOrThrow()
                }

        return network.surveyAnswer(surveyResponseJson = sendSurveyResponseJson).let { response ->
            if (response.isFailure) {
                Timber.e(response.exceptionOrNull())
                false
            } else response.getOrThrow().toBoolean()
        }
    }
}
