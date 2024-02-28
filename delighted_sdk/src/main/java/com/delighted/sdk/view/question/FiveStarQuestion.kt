package com.delighted.sdk.view.question

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.databinding.ViewFiveStarBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class FiveStarQuestion(theme: SurveyTheme) : QuestionWidget<ViewFiveStarBinding>(theme) {
    @LayoutRes
    override val layoutId: Int = layout.view_five_star

    private lateinit var viewBinding: ViewFiveStarBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): ViewFiveStarBinding {
        viewBinding = ViewFiveStarBinding.bind(inflateWidget(parent = parent)).apply {
            val stars = listOf(oneStar, twoStar, threeStar, fourStar, fiveStar)
            stars.forEachIndexed { index, star ->
                star.isChecked = false // all default to "off"
                star.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        stars.selectStars(selectedStar = index)
                        setAnswerValue(index + 1) // answer values is 1-indexed, not 0-indexed
                    }
                    true // consume this touch
                }
            }
            // apply theme
            theme.applyDltTheme {
                applyStarStateful(stars = stars, resources = root.context.resources)
            }
        }

        return viewBinding
    }

    private fun List<ToggleButton>.selectStars(selectedStar: Int) {
        this.forEachIndexed { index, toggleButton ->
            toggleButton.isChecked = index <= selectedStar
        }
    }

    override fun getWidget() = viewBinding
}
