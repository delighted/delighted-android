package com.delighted.sdk.interactor

import android.content.Context
import android.content.res.Configuration
import com.delighted.sdk.LocalCache
import com.delighted.sdk.SdkConfigResult.SdkConfigFail
import com.delighted.sdk.SdkConfigResult.SdkConfigSuccess
import com.delighted.sdk.SurveyInitResult
import com.delighted.sdk.SurveyInitResult.ConfigFail
import com.delighted.sdk.SurveyInitResult.CreationAlreadyInProgress
import com.delighted.sdk.SurveyInitResult.DeviceNotInPortrait
import com.delighted.sdk.SurveyInitResult.NotEligible
import com.delighted.sdk.SurveyInitResult.SurveyAlreadyInProgress
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Pass
import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.NotActive
import com.delighted.sdk.repository.SurveyInitRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.time.Instant
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
 * Creates a request for a new survey and launches that request. If successful the new survey
 * and survey configuration data are stored as state.
 */
internal class CreateSurveyUseCase @Inject constructor(
    private val surveyInitRepo: SurveyInitRepository,
    private val surveyFlowRepo: SurveyFlowRepository,
    private val localCache: LocalCache,
    private val clientEligibilityCheckUseCase: ClientEligibilityCheckUseCase,
    private val getSdkConfigurationUseCase: GetSdkConfigurationUseCase,
    private val surveyRequestUseCase: SurveyRequestUseCase,
    @ApplicationContext private val context: Context,
) : SuspendableUseCase<Unit, SurveyInitResult> {

    private var lock = AtomicBoolean(false)

    /**
     * Attempts to start a survey. This method suspends (makes network calls, etc.) and returns a
     * result state that indicates a survey was created and fetched successfully or some
     * variation of an error occurred. This method prevents more than one request being initiated
     * at a time.
     * @return [SurveyInitResult] A result state indicating what happened
     */
    override suspend fun execute(params: Unit): SurveyInitResult {
        // make sure a survey is not in progress
        if (surveyFlowRepo.surveyState.value !is NotActive) return SurveyAlreadyInProgress

        // make sure a request isn't already in progress
        if (lock.get()) return CreationAlreadyInProgress
        lock.set(true)

        // is device in portrait?
        if (context.resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT) {
            lock.set(false)
            return DeviceNotInPortrait
        }

        // step 1 - attempt to get config
        when (val sdkConfigurationResult = getSdkConfigurationUseCase()) {
            is SdkConfigFail -> {
                lock.set(false)
                return ConfigFail(throwable = sdkConfigurationResult.throwable)
            }
            is SdkConfigSuccess -> {
                surveyInitRepo.sdkConfiguration = sdkConfigurationResult.sdkConfiguration
            }
        }

        // step 2 - client eligibility check
        when (val eligibilityCheckResult = clientEligibilityCheckUseCase()) {
            is Fail -> {
                lock.set(false)
                return NotEligible(reason = eligibilityCheckResult)
            }
            is Pass -> {
                Timber.d("Client eligibility check passed, reason = $eligibilityCheckResult")
                // Mark last survey attempt
                localCache.lastSurveyedTimestamp = Instant.now().toEpochMilli()
            }
        }

        // step 3 - send survey creation request
        val surveyRequest = surveyRequestUseCase()
        lock.set(false)
        return surveyRequest
    }
}
