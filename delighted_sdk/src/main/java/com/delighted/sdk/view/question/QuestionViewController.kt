package com.delighted.sdk.view.question

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.domain.response.SurveyTypeIdentifier
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.CES
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.CES7
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.CSAT
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.CSAT3
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.ENPS
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.NPS
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.PMF
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.SMILEYS
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.STARS_FIVE
import com.delighted.sdk.domain.response.SurveyTypeIdentifier.THUMBS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class QuestionViewController @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val _choiceEvent = MutableSharedFlow<Int>(replay = 1)
    val choiceEvent: Flow<Int> get() = _choiceEvent

    private var widgetViewBinding: QuestionWidget<*>? = null

    fun setSurveyType(
        surveyTypeIdentifier: SurveyTypeIdentifier,
        shouldUseSlider: Boolean,
        choiceIndexes: List<Int>,
        theme: SurveyTheme
    ) {
        widgetViewBinding = if (shouldUseSlider) {
            SliderQuestion(choiceIndexes, theme)
        } else {
            when (surveyTypeIdentifier) {
                NPS -> NumberedButtonsQuestion(choiceIndexes, theme)
                STARS_FIVE -> FiveStarQuestion(theme)
                CSAT -> NumberedButtonsQuestion(choiceIndexes, theme)
                CSAT3 -> Csat3ButtonsQuestion(theme)
                CES -> NumberedButtonsQuestion(choiceIndexes, theme)
                CES7 -> NumberedButtonsQuestion(choiceIndexes, theme)
                ENPS -> NumberedButtonsQuestion(choiceIndexes, theme)
                SMILEYS -> SmileysQuestion(theme)
                THUMBS -> ThumbsQuestion(theme)
                PMF -> PmfQuestion(theme)
            }
        }
        widgetViewBinding?.setupWidget(parent = this) { answerValue ->
            _choiceEvent.tryEmit(
                answerValue
            )
        }
    }

    /**
     * Returns the question view object, if it is Slider type or Numbered Button type, as a Scale
     * type. Returns null if question view is any other type
     */
    fun getScale(): ScaleQuestion? = widgetViewBinding as? ScaleQuestion
}
