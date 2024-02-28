package com.delighted.sdk.repository

import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.domain.SdkConfiguration
import com.delighted.sdk.domain.response.PusherConfigResponse
import com.delighted.sdk.domain.response.SurveyRequestResponse
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState
import kotlinx.coroutines.flow.StateFlow

/**
 * Repository for data that is created or consumed when a user takes a survey. Also keeps track of
 * [SurveyState]. Data stored here pertains to one initiated survey at a time.
 */
internal interface SurveyFlowRepository {
    val survey: SurveyRequestResponse?
    fun setSurvey(survey: SurveyRequestResponse)
    val surveyState: StateFlow<SurveyState>

    fun reset()
    fun storeFirstQuestionScore(score: Int)
    fun storeFirstQuestionComment(comment: String)
    fun getFirstQuestionAnswerData(): AnswerData
    fun completedFirstQuestion(isAdditionalQuestions: Boolean)
    fun storeAdditionalQuestionAnswer(questionId: String, answerData: String)
    fun getAdditionalQuestionAnswer(questionId: String): String?
    fun connectedToPusher(connected: Boolean)

    sealed class SurveyState {
        data class ClientEligibilityPassed(
            val sdkInitParams: SdkInitParams,
            val sdkConfig: SdkConfiguration
        ) : SurveyState()

        object ConnectingPusher : SurveyState()
        object FirstQuestion : SurveyState()
        object ThankYou : SurveyState()
        object AdditionalQuestions : SurveyState()
        object NotActive : SurveyState()
    }

    data class AnswerData(
        val score: Int? = null,
        val comment: String? = null
    )

    // convenience
    fun getSurveyToken(): Result<String>
    fun getPusher(): Result<PusherConfigResponse>
}
