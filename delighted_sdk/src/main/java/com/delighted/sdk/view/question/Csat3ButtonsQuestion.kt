package com.delighted.sdk.view.question

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.core.checkThisUncheckOthers
import com.delighted.sdk.databinding.ViewCsat3NumberedButtonsBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class Csat3ButtonsQuestion(
    theme: SurveyTheme,
) : QuestionWidget<ViewCsat3NumberedButtonsBinding>(theme) {
    @LayoutRes
    override val layoutId: Int = layout.view_csat3_numbered_buttons

    private lateinit var viewBinding: ViewCsat3NumberedButtonsBinding

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): ViewCsat3NumberedButtonsBinding {
        viewBinding = ViewCsat3NumberedButtonsBinding.bind(inflateWidget(parent = parent)).apply {
            // add numbered buttons to linear layout
            val smileys = listOf(dissatisfied, neither, satisfied)
            // set all to selected
            smileys.forEach { it.isChecked = true }
            // apply theme
            theme.applyDltTheme { applyBaseIconStateful(smileys) }
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
