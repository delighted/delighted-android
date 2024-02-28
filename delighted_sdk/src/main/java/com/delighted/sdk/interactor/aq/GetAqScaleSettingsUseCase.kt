package com.delighted.sdk.interactor.aq

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SCALE
import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.aq.GetAqScaleSettingsUseCase.AqScaleSettings
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqScaleSettingsUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<String, AqScaleSettings> {
    override fun execute(params: String): AqScaleSettings {
        val question = surveyFlowRepo.survey
            ?.surveyResponse
            ?.surveyTemplateResponse
            ?.fromResponse()
            ?.additionalQuestions
            ?.find { it.id == params }

        requireNotNull(question) { "No additional question with id $params" }
        require(question.type == SCALE) { "Additional question must be 'scale' type" }
        return AqScaleSettings(
            scaleMin = question.scaleMin ?: -1,
            scaleMinLabel = question.scaleMinLabel.orEmpty(),
            scaleMax = question.scaleMax ?: -1,
            scaleMaxLabel = question.scaleMaxLabel.orEmpty()
        )
    }

    data class AqScaleSettings(
        val scaleMin: Int,
        val scaleMinLabel: String,
        val scaleMax: Int,
        val scaleMaxLabel: String,
    )
}
