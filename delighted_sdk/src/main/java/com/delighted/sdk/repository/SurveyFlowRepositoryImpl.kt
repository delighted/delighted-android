package com.delighted.sdk.repository

import com.delighted.sdk.domain.response.PusherConfigResponse
import com.delighted.sdk.domain.response.SurveyRequestResponse
import com.delighted.sdk.repository.SurveyFlowRepository.AnswerData
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.AdditionalQuestions
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ConnectingPusher
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.FirstQuestion
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.NotActive
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ThankYou
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

internal class SurveyFlowRepositoryImpl @Inject constructor() : SurveyFlowRepository {

    private var _surveyState = MutableStateFlow<SurveyState>(NotActive)
    private var _surveyRequestResponse: SurveyRequestResponse? = null

    private var firstQuestionScore: Int? = null
    private var firstQuestionComment: String? = null

    private val additionalQuestionAnswers = mutableMapOf<String, String>()

    override fun reset() {
        _surveyRequestResponse = null
        firstQuestionScore = null
        firstQuestionComment = null
        additionalQuestionAnswers.clear()
        _surveyState.value = NotActive
    }

    override fun storeFirstQuestionScore(score: Int) {
        firstQuestionScore = score
    }

    override fun storeFirstQuestionComment(comment: String) {
        firstQuestionComment = comment
    }

    override fun getFirstQuestionAnswerData(): AnswerData {
        return AnswerData(firstQuestionScore, firstQuestionComment)
    }

    override fun storeAdditionalQuestionAnswer(questionId: String, answerData: String) {
        additionalQuestionAnswers[questionId] = answerData
    }

    override fun getAdditionalQuestionAnswer(questionId: String): String? {
        return additionalQuestionAnswers[questionId]
    }

    override fun completedFirstQuestion(isAdditionalQuestions: Boolean) {
        if (isAdditionalQuestions) {
            _surveyState.value = AdditionalQuestions
        } else {
            _surveyState.value = ThankYou
        }
    }

    override val survey: SurveyRequestResponse?
        get() = _surveyRequestResponse

    override fun setSurvey(survey: SurveyRequestResponse) {
        _surveyRequestResponse = survey
        _surveyState.value = ConnectingPusher
    }

    override fun connectedToPusher(connected: Boolean) {
        if (!connected) Timber.e("Failed to connect to Pusher service")
        _surveyState.value = FirstQuestion
    }

    override fun getSurveyToken(): Result<String> {
        val token = _surveyRequestResponse?.token
        return if (token != null) Result.success(token)
        else Result.failure(Exception("No survey token present"))
    }

    override fun getPusher(): Result<PusherConfigResponse> {
        val pusher = survey?.surveyResponse?.surveyConfigurationResponse?.pusher
        return if (pusher != null) Result.success(pusher)
        else Result.success(
            PusherConfigResponse(
                enabled = false,
                channelName = null,
                webSocketUrl = null
            )
        )
    }

    override val surveyState: StateFlow<SurveyState> get() = _surveyState
}
