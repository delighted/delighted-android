package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.request.AdditionalQuestionAnswer
import com.delighted.sdk.domain.request.SurveyAnswerRequest
import com.delighted.sdk.interactor.GetTokenFromSurveyUseCase
import com.delighted.sdk.interactor.SuspendableUseCase
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.network.JsonHandler
import com.delighted.sdk.network.NetworkFetch
import com.delighted.sdk.repository.SurveyFlowRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Send the answer data for an additional question to the backend
 */
internal class SendAqAnswerUseCase @Inject constructor(
    private val network: NetworkFetch,
    private val jsonHandler: JsonHandler,
    private val getTokenFromSurveyUseCase: GetTokenFromSurveyUseCase,
    private val surveyFlowRepo: SurveyFlowRepository,
) : SuspendableUseCase<String, Boolean> {

    /**
     * @param params The id of an additional question
     */
    override suspend fun execute(params: String): Boolean {
        val answerData = surveyFlowRepo.getAdditionalQuestionAnswer(questionId = params)

        if (answerData == null) {
            Timber.d("Answer required for additional question $params before sending to backend")
            return false
        }

        val surveyAnswerRequest = SurveyAnswerRequest(
            surveyRequestToken = getTokenFromSurveyUseCase(),
            additionalQuestionAnswers = listOf(
                AdditionalQuestionAnswer(
                    id = params,
                    value = answerData
                )
            )
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
