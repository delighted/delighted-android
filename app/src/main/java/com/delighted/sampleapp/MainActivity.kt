package com.delighted.sampleapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.delighted.databinding.ActivityMainBinding
import com.delighted.sampleapp.core.viewBinding
import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.SurveyInitResult.ConfigFail
import com.delighted.sdk.SurveyInitResult.CreationAlreadyInProgress
import com.delighted.sdk.SurveyInitResult.DeviceNotInPortrait
import com.delighted.sdk.SurveyInitResult.NotEligible
import com.delighted.sdk.SurveyInitResult.SurveyAlreadyInProgress
import com.delighted.sdk.SurveyInitResult.SurveyRequestFail
import com.delighted.sdk.SurveyInitResult.SurveyReturned
import com.google.android.material.snackbar.Snackbar
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel>()
    private val surveyButtonsAdapter = SurveyButtonsAdapter()

    private val config: AppConfig get() = (application as DelightedSampleApp).config

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!AppCenter.isConfigured()) {
            AppCenter.start(
                application,
                config.appCenterSecret,
                Analytics::class.java,
                Crashes::class.java
            )
        }

        initRecyclerView()
    }

    private fun displaySnack(message: String) {
        Snackbar.make(
            this@MainActivity,
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private fun initRecyclerView() {
        binding.surveyButtons.layoutManager = LinearLayoutManager(this)
        binding.surveyButtons.adapter = surveyButtonsAdapter
        MainScope().launch {
            surveyButtonsAdapter.triggerId.collect { id ->
                if (id.isBlank()) {
                    // display about
                    displaySnack(message = viewModel.getAboutInfo())
                } else {
                    launchSurvey(id = id)
                }
            }
        }

        // hardcoded QA projects setup
        val surveyNamesAndIds = listOf(
            "NPS" to "mobile-sdk-MZI7JiItaVEhcDOz",
            "CES (5-point)" to "mobile-sdk-lLP3R1ZrvjLVTBEL",
            "CSAT" to "mobile-sdk-on3ADzMqczZP2V2O",
            "5-star" to "mobile-sdk-KOXlnq0hBwViN9Nu",
            "Smileys" to "mobile-sdk-WFdiU1PF6b3AuMB4",
            "Thumbs" to "mobile-sdk-bdN0fT9GZpXsMqxI",
            "eNPS" to "mobile-sdk-cJmG8vTg0CDPazwF",
            "PMF" to "mobile-sdk-Tk5WTarzkYd5uCTG",
            "CSAT (3-point)" to "mobile-sdk-LCTlXgXo7XAoIKog",
            "CES" to "mobile-sdk-knM3lJ5zGBEraZG8",
            "About" to "",
        )

        surveyButtonsAdapter.submitList(surveyNamesAndIds)
    }

    /**
     * Host App Example. This is an exhaustive way to deal with creating and launching the survey
     * and handling the results. createAndLaunchSurvey() will launch the Survey SDK Activity if
     * configuration is successful and still return the successful result [SurveyReturned].
     * All other non-success states get returned here, too.
     *
     * The SDK enforces that the current activity/context information must be consumed at
     * the point of survey creation so as not to allow the host app to accidentally initiate survey
     * UI for which there has not been a survey properly configured.
     */
    private suspend fun launchSurvey(id: String) {
        when (
            val result = viewModel.surveySdk.createAndLaunchSurvey(
                context = this@MainActivity,
                options = null,
                sdkInitParams = SdkInitParams(
                    delightedId = id,
                    token = null,
                    createdAt = null,
                    initialDelay = null,
                    recurringPeriod = null,
                    themeOverride = null,
                    testMode = true
                )
            )
        ) {
            is ConfigFail -> {
                Timber.e(result.throwable)
                displaySnack(message = "Failed to get config")
            }
            is NotEligible -> {
                Timber.e(message = result.reason.toString())
                displaySnack(message = "Client eligibility check failed: ${result.reason::class.simpleName}")
            }
            is SurveyRequestFail -> {
                Timber.e(result.throwable)
                displaySnack(message = "Survey creation failed")
            }
            is SurveyReturned -> {
                displaySnack(message = "Survey returned!")
            }
            CreationAlreadyInProgress -> {
                Timber.d("Rejecting request because survey CREATION already in progress")
                displaySnack(message = "Rejecting request because survey CREATION already in progress")
            }
            SurveyAlreadyInProgress -> {
                Timber.d("Rejecting request because a survey is already in progress")
                displaySnack(message = "Rejecting request because a survey is already in progress")
            }
            DeviceNotInPortrait -> {
                Timber.d("Rejecting request because the device is not in 'portrait' orientation")
                displaySnack(message = "Rejecting request because the device is not in 'portrait' orientation")
            }
        }
    }
}
