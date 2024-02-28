package com.delighted.sdk.view.question

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.core.checkThisUncheckOthers
import com.delighted.sdk.databinding.ViewNumberedButtonBinding
import com.delighted.sdk.databinding.ViewNumberedButtonsBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class NumberedButtonsQuestion(
    private val choiceIndexes: List<Int>,
    theme: SurveyTheme,
) : QuestionWidget<ViewNumberedButtonsBinding>(theme), ScaleQuestion {
    @LayoutRes
    override val layoutId: Int = layout.view_numbered_buttons

    private lateinit var viewBinding: ViewNumberedButtonsBinding
    private lateinit var numberedButtons: List<ViewNumberedButtonBinding>

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): ViewNumberedButtonsBinding {
        viewBinding = ViewNumberedButtonsBinding.bind(inflateWidget(parent = parent)).apply {
            // add numbered buttons to linear layout
            numberedButtons =
                choiceIndexes.map { choiceValue ->
                    val buttonView = LayoutInflater.from(parent.context)
                        .inflate(layout.view_numbered_button, root, false)
                    val numberedButtonBinding = ViewNumberedButtonBinding.bind(buttonView)
                    with(numberedButtonBinding) {
                        // both states have same color font
                        toggleButton.textOn = choiceValue.toString()
                        toggleButton.textOff = choiceValue.toString()
                    }
                    this.root.addView(buttonView)
                    numberedButtonBinding
                }
            // set them all to "on" state
            // ensure action is performed on the ui thread to ensure action happens after
            // binding is complete, otherwise state change does not update background color
            Handler(Looper.getMainLooper()).post {
                numberedButtons.forEach { it.toggleButton.isChecked = true }
            }
            // apply theme
            theme.applyDltTheme {
                applyScaleStateful(
                    buttons = numberedButtons.map { it.toggleButton },
                    resources = root.context.resources
                )
            }
            // a click on this one sets all others to "off" state, this goes to "on"
            numberedButtons.forEachIndexed { index, childView ->
                childView.toggleButton.setOnClickListener {
                    numberedButtons.map { it.toggleButton }
                        .checkThisUncheckOthers(thisIndex = index)
                    setAnswerValue(choiceIndexes[index]) // answer values is 1-indexed, not 0-indexed
                }
            }
        }
        return viewBinding
    }

    override fun getWidget() = viewBinding

    override fun setValue(value: Int) {
        if (value == SliderQuestion.NO_VALUE_SELECTED) {
            // set them all to "off" state
            numberedButtons.forEach { childView -> childView.toggleButton.isChecked = false }
            return
        }
        choiceIndexes.indexOf(value).let { index ->
            numberedButtons.map { it.toggleButton }.checkThisUncheckOthers(thisIndex = index)
        }
    }
}
