package com.delighted.sdk.interactor.aq

import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.FirstAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.LastAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.MiddleAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.OnlyAq
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqPrimaryButtonLabelUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getAqPositionUseCase: GetAqPositionUseCase,
) :
    UseCase<String, String> {
    override fun execute(params: String): String {
        return surveyFlowRepo.survey?.let { survey ->
            val nextText = survey
                .surveyResponse
                .surveyConfigurationResponse
                .nextText
            val doneText = survey
                .surveyResponse
                .surveyConfigurationResponse
                .doneText
            when (getAqPositionUseCase.execute(params = params)) {
                LastAq, OnlyAq -> doneText
                FirstAq, MiddleAq -> nextText
                else -> null
            }
        }.orEmpty()
    }
}
