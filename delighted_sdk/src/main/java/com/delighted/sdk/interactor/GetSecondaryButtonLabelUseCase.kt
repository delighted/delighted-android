package com.delighted.sdk.interactor

import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the appropriate text for the secondary button
 */
internal class GetSecondaryButtonLabelUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, String> {
    override fun execute(params: Unit): String =
        surveyFlowRepo.survey?.surveyResponse?.surveyConfigurationResponse?.prevText.orEmpty()
}
