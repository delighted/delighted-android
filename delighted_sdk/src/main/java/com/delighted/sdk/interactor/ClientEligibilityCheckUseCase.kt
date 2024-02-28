package com.delighted.sdk.interactor

import com.delighted.sdk.LocalCache
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.InitialDelay
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.NotEnabled
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.PlanExhausted
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.RandomSampleFactor
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.RecurringLessThanMinimum
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.RecurringPeriod
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.RecurringSurveyDisabled
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Pass.ForceDisplay
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Pass.Passed
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Pass.TestModeEnabled
import com.delighted.sdk.repository.SurveyInitRepository
import timber.log.Timber
import java.time.Instant
import javax.inject.Inject
import kotlin.random.Random

class ClientEligibilityCheckUseCase @Inject internal constructor(
    private val surveyInitRepo: SurveyInitRepository,
    private val localCache: LocalCache
) : UseCase<Unit, ClientEligibilityCheckResult> {

    override fun execute(params: Unit): ClientEligibilityCheckResult {
        val sdkInitParams = surveyInitRepo.sdkInitParams
        val sdkConfig = surveyInitRepo.sdkConfiguration

        // Don't do check if in test mode
        if (sdkInitParams.testMode) return TestModeEnabled

        // Fail if not ANDROID enabled
        if (!sdkConfig.androidEnabled) return NotEnabled

        // Fail if plan exhausted
        if (sdkConfig.planLimitExhausted) return PlanExhausted

        // Skip checking if force display
        if (sdkConfig.forceDisplay) {
            return ForceDisplay
        } else {
            // Skip checking if no created at or last surveyed
            if (localCache.isSurveyed) {

                // Fail when a person has been surveyed but recurring surveys aren't enabled
                val recurring = sdkInitParams.recurringPeriod?.toLong()
                    ?: sdkConfig.recurringSurveyPeriod?.toLong()
                    ?: return RecurringSurveyDisabled

                val lastSurveyed = Instant.ofEpochMilli(localCache.lastSurveyedTimestamp)
                // Fail if less than recurring time
                if (lastSurveyed.plusSeconds(recurring).isAfter(Instant.now())) {
                    return RecurringPeriod
                }

                // Fail if recurring period less than minimum survey interval
                // Note: this will only ever fail if developer override caused this to happen
                if (recurring < sdkConfig.minSurveyInterval) {
                    return RecurringLessThanMinimum
                }
            } else {
                val createAtOrFirstSeen =
                    Instant.ofEpochMilli(
                        sdkInitParams.createdAt ?: localCache.firstSeenTimestamp
                    )

                // Fail if last seen is less than initial delay.
                val initialDelay = sdkInitParams.initialDelay ?: sdkConfig.initialSurveyDelay
                if (createAtOrFirstSeen
                    .plusSeconds(initialDelay.toLong())
                    .isAfter(Instant.now())
                ) {
                    return InitialDelay
                }
            }
        }

        return doRandomSampleFactor(
            sampleFactor = sdkConfig.sampleFactor
        )
    }

    private fun doRandomSampleFactor(
        sampleFactor: Double,
    ): ClientEligibilityCheckResult =
        Random.nextDouble().let { random ->
            // Pass if random number is less than the sample factor
            return if (random <= sampleFactor) {
                Timber.d("Eligibility passed because $random <= $sampleFactor")
                Passed
            } else {
                Timber.d("Eligibility failed because $random) > $sampleFactor")
                RandomSampleFactor
            }
        }

    sealed class ClientEligibilityCheckResult {
        sealed class Pass : ClientEligibilityCheckResult() {
            object TestModeEnabled : Pass()
            object ForceDisplay : Pass()
            object Passed : Pass()
        }

        sealed class Fail : ClientEligibilityCheckResult() {
            object NotEnabled : Fail()
            object PlanExhausted : Fail()
            object RandomSampleFactor : Fail()
            object RecurringSurveyDisabled : Fail()
            object RecurringPeriod : Fail()
            object RecurringLessThanMinimum : Fail()
            object InitialDelay : Fail()
        }
    }
}
