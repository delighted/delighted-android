package com.delighted.sdk.domain

import com.delighted.sdk.LocalCache
import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail.InitialDelay
import com.delighted.sdk.interactor.ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Pass
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.repository.SurveyInitRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.Instant
import kotlin.test.assertEquals

internal class ClientEligibilityCheckUseCaseTest {

    private lateinit var instance: ClientEligibilityCheckUseCase
    private lateinit var localCache: LocalCache
    private lateinit var surveyInitRepo: SurveyInitRepository

    private val delightedId: String = "sampleId"
    private val baseInitParams = SdkInitParams(delightedId = delightedId)

    @Before
    fun setUp() {
        localCache = mockk()
        surveyInitRepo = mockk()
        every { surveyInitRepo.sdkInitParams } returns baseInitParams
        instance =
            ClientEligibilityCheckUseCase(localCache = localCache, surveyInitRepo = surveyInitRepo)
    }

    @Test
    fun `elig check passes because developer has test mode on`() {
        // Setup
        every { surveyInitRepo.sdkInitParams } returns baseInitParams.copy(testMode = true)
        every { surveyInitRepo.sdkConfiguration } returns mockk()

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Pass.TestModeEnabled, actual = result)
    }

    @Test
    fun `elig check fails because survey not enabled`() {
        // Setup
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns false
        }

        // Test
        val result =
            instance()

        // Verify
        assertEquals(expected = Fail.NotEnabled, actual = result)
    }

    @Test
    fun `elig check fails because plan limit is exhausted`() {
        // Setup
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns true
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.PlanExhausted, actual = result)
    }

    @Test
    fun `elig check passes because force display is true`() {
        // Setup
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns true
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Pass.ForceDisplay, actual = result)
    }

    @Test
    fun `elig check fails because user has been surveyed but recurring not allowed `() {
        // Setup
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns null
        }
        every { localCache.isSurveyed } returns true

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.RecurringSurveyDisabled, actual = result)
    }

    @Test
    fun `elig check fails because user has been surveyed but not enough time has passed`() {
        // Setup
        every { localCache.isSurveyed } returns true
        every { localCache.lastSurveyedTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns 6 // survey every 6 seconds
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.RecurringPeriod, actual = result)
    }

    @Test
    fun `elig check fails because recurring survey period is less than min survey interval`() {
        // Setup
        every { localCache.isSurveyed } returns true
        every { localCache.lastSurveyedTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns 3 // survey every 4 seconds, server would never send this
            every { minSurveyInterval } returns 4
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.RecurringLessThanMinimum, actual = result)
    }

    @Test
    fun `elig check fails because recurring survey period via config is less than min survey interval but is overridden`() {
        // Setup
        every { localCache.isSurveyed } returns true
        every { localCache.lastSurveyedTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkInitParams } returns baseInitParams.copy(recurringPeriod = 3)
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns 5 // this will pass the check unless it is overridden
            every { minSurveyInterval } returns 4 // survey every 4 seconds
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.RecurringLessThanMinimum, actual = result)
    }

    @Test
    fun `elig check fails because new survey is within initial delay via 'created at'`() {
        // Setup
        every { surveyInitRepo.sdkInitParams } returns baseInitParams.copy(createdAt = fiveSecondsAgo())
        every { localCache.isSurveyed } returns false
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { initialSurveyDelay } returns 6 // supposed to wait 6 seconds from last
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = InitialDelay, actual = result)
    }

    @Test
    fun `elig check fails because new survey is within initial delay via local cache 'first seen'`() {
        // Setup
        every { localCache.isSurveyed } returns false
        every { localCache.firstSeenTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { initialSurveyDelay } returns 6 // supposed to wait 6 seconds from last
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = InitialDelay, actual = result)
    }

    @Test
    fun `elig check fails because new survey is within initial delay override`() {
        // Setup
        every { surveyInitRepo.sdkInitParams } returns baseInitParams.copy(initialDelay = 6) // supposed to wait 6 seconds from last
        every { localCache.isSurveyed } returns false
        every { localCache.firstSeenTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { initialSurveyDelay } returns 0 // should be overridden
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = InitialDelay, actual = result)
    }

    @Test
    fun `elig check fails because of sample factor`() {
        // Setup
        every { localCache.isSurveyed } returns true
        every { localCache.lastSurveyedTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns 4
            every { minSurveyInterval } returns 3
            every { sampleFactor } returns 0.0
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Fail.RandomSampleFactor, actual = result)
    }

    @Test
    fun `elig check passes all checks happy path`() {
        // Setup
        every { localCache.isSurveyed } returns true
        every { localCache.lastSurveyedTimestamp } returns fiveSecondsAgo()
        every { surveyInitRepo.sdkConfiguration } returns mockk {
            every { androidEnabled } returns true
            every { planLimitExhausted } returns false
            every { forceDisplay } returns false
            every { recurringSurveyPeriod } returns 4
            every { minSurveyInterval } returns 3
            every { sampleFactor } returns 1.0
        }

        // Test
        val result = instance()

        // Verify
        assertEquals(expected = Pass.Passed, actual = result)
    }

    private fun fiveSecondsAgo() = Instant.now().toEpochMilli() - 5_000
}
