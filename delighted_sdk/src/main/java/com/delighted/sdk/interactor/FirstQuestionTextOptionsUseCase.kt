package com.delighted.sdk.interactor

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.CSAT3
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.PMF
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.SMILEYS
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.STARS_FIVE
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.THUMBS
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.None
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.ThreeLabels
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.TwoLabels
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieves the labels and label configuration information for the First Question of a survey.
 * A survey First Question can have no labels, two labels, or three, depending on the survey type.
 */
internal class FirstQuestionTextOptionsUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, FirstQuestionTextOptions> {
    override fun execute(params: Unit): FirstQuestionTextOptions {
        val surveyType = surveyFlowRepo.survey?.surveyResponse?.surveyTypeResponse?.let {
            fromResponse(it)
        }?.id
        return when (surveyType) {
            PMF, CSAT3 -> {
                val surveyTemplate =
                    surveyFlowRepo.survey?.surveyResponse?.surveyTemplateResponse?.fromResponse()
                if (surveyTemplate == null) {
                    None
                } else {
                    ThreeLabels(
                        start = surveyTemplate.scoreText[1].orEmpty(),
                        middle = surveyTemplate.scoreText[2].orEmpty(),
                        end = surveyTemplate.scoreText[3].orEmpty()
                    )
                }
            }
            STARS_FIVE, THUMBS, SMILEYS -> None
            else -> {
                val surveyConfig =
                    surveyFlowRepo.survey
                        ?.surveyResponse
                        ?.surveyConfigurationResponse
                if (surveyConfig == null) {
                    None
                } else {
                    TwoLabels(
                        likely = surveyConfig.veryLikelyText,
                        notLikely = surveyConfig.notLikelyText
                    )
                }
            }
        }
    }

    sealed class FirstQuestionTextOptions {
        data class ThreeLabels(
            val start: String,
            val middle: String,
            val end: String
        ) : FirstQuestionTextOptions()

        data class TwoLabels(
            val likely: String,
            val notLikely: String
        ) : FirstQuestionTextOptions()

        object None : FirstQuestionTextOptions()
    }
}
