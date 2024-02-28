package com.delighted.sdk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.delighted.sdk.SurveyInitResult.SurveyReturned
import com.delighted.sdk.interactor.CreateSurveyUseCase
import com.delighted.sdk.interactor.SetSdkInitParametersUseCase
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.view.activity.DelightedActivity
import javax.inject.Inject

/**
 * The Delighted Android SDK entry point
 */
class SurveySdk @Inject internal constructor(
    private val setSdkInitParametersUseCase: SetSdkInitParametersUseCase,
    private val createSurveyUseCase: CreateSurveyUseCase,
) {
    /**
     * @param context Android context
     * @param options Any options to be included for startActivity()
     * @param sdkInitParams the initial parameters for the survey
     */
    suspend fun createAndLaunchSurvey(
        context: Context,
        options: Bundle? = null,
        sdkInitParams: SdkInitParams
    ): SurveyInitResult {
        setSdkInitParametersUseCase(params = sdkInitParams)
        val surveyInitResult = createSurveyUseCase()
        if (surveyInitResult == SurveyReturned) {
            val intent = Intent(context, DelightedActivity::class.java)
            startActivity(context, intent, options)
        }
        return surveyInitResult
    }
}
