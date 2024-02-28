package com.delighted.sdk.interactor

import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyInitRepository
import javax.inject.Inject

/**
 * Kill the SDK properly. Discard any stateful data and close all resources and network connections.
 */
internal class KillSdkUseCase @Inject constructor(
    private val surveyInitRepo: SurveyInitRepository,
    private val surveyFlowRepo: SurveyFlowRepository,
) :
    UseCase<Unit, Unit> {
    override fun execute(params: Unit) {
        surveyInitRepo.reset()
        surveyFlowRepo.reset()
    }
}
