package com.delighted.sdk.interactor

import com.delighted.sdk.interactor.GetSurveyDisplayUseCase.SurveyDisplay
import com.delighted.sdk.interactor.GetSurveyDisplayUseCase.SurveyDisplay.Card
import com.delighted.sdk.interactor.GetSurveyDisplayUseCase.SurveyDisplay.Modal
import javax.inject.Inject

/**
 * Retrieves the survey's initial display condition: as dialog ([Modal]) or as bottom sheet [Card]
 */
internal class GetSurveyDisplayUseCase @Inject constructor(
    private val getSurveyThemeUseCase: GetSurveyThemeUseCase,
) :
    UseCase<Unit, SurveyDisplay> {
    override fun execute(params: Unit): SurveyDisplay {
        val theme = getSurveyThemeUseCase()

        return when (theme.display) {
            "card" -> Card
            "modal" -> Modal
            else -> Card
        }
    }

    sealed class SurveyDisplay {
        object Modal : SurveyDisplay()
        object Card : SurveyDisplay()
    }
}
