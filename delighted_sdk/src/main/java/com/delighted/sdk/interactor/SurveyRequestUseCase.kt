package com.delighted.sdk.interactor

import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.SurveyInitResult
import com.delighted.sdk.SurveyInitResult.SurveyRequestFail
import com.delighted.sdk.SurveyInitResult.SurveyReturned
import com.delighted.sdk.domain.request.SurveyRequest
import com.delighted.sdk.network.JsonHandler
import com.delighted.sdk.network.NetworkFetch
import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyInitRepository
import javax.inject.Inject

/**
 * Sends a request for a new survey, stores it in data layer upon success
 */
internal class SurveyRequestUseCase @Inject constructor(
    private val surveyInitRepo: SurveyInitRepository,
    private val surveyFlowRepo: SurveyFlowRepository,
    private val network: NetworkFetch,
    private val jsonHandler: JsonHandler,
) : SuspendableUseCase<Unit, SurveyInitResult> {
    override suspend fun execute(params: Unit): SurveyInitResult {
        val surveyRequest =
            surveyRequestFromInitParams(sdkInitParams = surveyInitRepo.sdkInitParams)
        val surveyRequestJson = jsonHandler.encodeSurveyRequest(surveyRequest = surveyRequest).let {
            if (it.isFailure) return SurveyRequestFail(throwable = it.exceptionOrNull())
            it.getOrThrow()
        }
        val surveyRequestResponse =
            network.requestSurvey(surveyRequestJson = surveyRequestJson)
                .let {
                    if (it.isFailure) return SurveyRequestFail(throwable = it.exceptionOrNull())
                    jsonHandler.attemptDecodeSurveyRequestResponse(rawJson = it.getOrThrow())
                }

        return if (surveyRequestResponse.isSuccess) {
            surveyFlowRepo.setSurvey(survey = surveyRequestResponse.getOrThrow())
            SurveyReturned
        } else {
            SurveyRequestFail(throwable = surveyRequestResponse.exceptionOrNull())
        }
    }

    private fun surveyRequestFromInitParams(sdkInitParams: SdkInitParams): SurveyRequest {
        return SurveyRequest(
            person = sdkInitParams.person,
            properties = sdkInitParams.properties,
            token = sdkInitParams.token,
            testMode = sdkInitParams.testMode
        )
    }
}
