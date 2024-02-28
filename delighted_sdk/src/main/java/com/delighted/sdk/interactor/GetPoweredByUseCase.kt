package com.delighted.sdk.interactor

import com.delighted.sdk.interactor.GetPoweredByUseCase.PoweredBy
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieve the text value for the 'Powered by Delighted' label normally displayed at the bottom
 * of the survey UI.
 */
internal class GetPoweredByUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, PoweredBy> {
    override fun execute(params: Unit): PoweredBy {
        val text = surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyConfigurationResponse
            ?.poweredByLinkText
        val link = surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyConfigurationResponse
            ?.poweredByLinkUrl
        return PoweredBy(text = text.orEmpty(), url = link.orEmpty())
    }

    data class PoweredBy(val text: String, val url: String)
}
