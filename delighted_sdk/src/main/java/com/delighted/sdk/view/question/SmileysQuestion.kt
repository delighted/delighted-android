package com.delighted.sdk.view.question

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.core.checkThisUncheckOthers
import com.delighted.sdk.databinding.ViewSmileysBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class SmileysQuestion(theme: SurveyTheme) : QuestionWidget<ViewSmileysBinding>(theme) {
    @LayoutRes
    override val layoutId: Int = layout.view_smileys

    private lateinit var viewBinding: ViewSmileysBinding

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (Int) -> Unit
    ): ViewSmileysBinding {
        viewBinding = ViewSmileysBinding.bind(inflateWidget(parent = parent)).apply {
            val smileys = listOf(veryUnhappy, unhappy, neutral, happy, veryHappy)
            // set them all to "selected" state to start
            smileys.forEach { it.isChecked = true }
            // apply theme
            theme.applyDltTheme { applyBaseIconStateful(views = smileys) }
            // a click on 'this' one sets all others to "off" state, 'this' goes to "on"
            smileys.forEachIndexed { index, toggleButton ->
                toggleButton.setOnClickListener {
                    smileys.checkThisUncheckOthers(thisIndex = index)
                    setAnswerValue(index + 1) // answer values is 1-indexed, not 0-indexed
                }
            }
        }
        return viewBinding
    }

    override fun getWidget() = viewBinding
}
