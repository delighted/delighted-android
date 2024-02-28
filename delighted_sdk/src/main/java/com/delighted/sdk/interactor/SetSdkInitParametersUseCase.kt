package com.delighted.sdk.interactor

import com.delighted.sdk.SdkInitParams
import com.delighted.sdk.repository.SurveyInitRepository
import javax.inject.Inject

/**
 * Stores the SDK init parameters set by the host app in the data layer
 */
internal class SetSdkInitParametersUseCase @Inject constructor(private val surveyInitRepo: SurveyInitRepository) :
    UseCase<SdkInitParams, Unit> {
    override fun execute(params: SdkInitParams) {
        surveyInitRepo.sdkInitParams = params
    }
}
