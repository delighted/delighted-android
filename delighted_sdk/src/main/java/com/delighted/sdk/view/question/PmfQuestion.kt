package com.delighted.sdk.view.question

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.core.checkThisUncheckOthers
import com.delighted.sdk.databinding.ViewPmfBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class PmfQuestion(theme: SurveyTheme) : QuestionWidget<ViewPmfBinding>(theme) {
    @LayoutRes
    override val layoutId: Int = layout.view_pmf

    private lateinit var viewBinding: ViewPmfBinding

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): ViewPmfBinding {
        viewBinding = ViewPmfBinding.bind(inflateWidget(parent = parent)).apply {
            val smileys = listOf(notDisappointed, disappointed, veryDisappointed)
            // set them all to "selected" state to start
            smileys.forEach { it.isChecked = true }
            // apply theme
            theme.applyDltTheme { applyBaseIconStateful(views = smileys) }
            // a click on 'this' one sets all others to "off" state, 'this' goes to "on"
            smileys.forEachIndexed { index, toggleButton ->
                toggleButton.setOnClickListener {
                    smileys.checkThisUncheckOthers(index)
                    setAnswerValue(index + 1) // answer values is 1-indexed, not 0-indexed
                }
            }
        }
        return viewBinding
    }

    override fun getWidget() = viewBinding
}
