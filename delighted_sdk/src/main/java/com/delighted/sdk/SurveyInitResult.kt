package com.delighted.sdk

import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult

/**
 * Result states when for when the SDK tries to create a survey for the user
 */
sealed class SurveyInitResult {
    data class ConfigFail(val throwable: Throwable?) : SurveyInitResult()
    data class NotEligible(val reason: ClientEligibilityCheckResult.Fail) : SurveyInitResult()
    data class SurveyRequestFail(val throwable: Throwable?) : SurveyInitResult()
    object SurveyReturned : SurveyInitResult()
    object CreationAlreadyInProgress : SurveyInitResult()
    object SurveyAlreadyInProgress : SurveyInitResult()
    object DeviceNotInPortrait : SurveyInitResult()
}
