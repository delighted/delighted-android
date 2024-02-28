package com.delighted.sdk.view.question

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.delighted.sdk.R.layout
import com.delighted.sdk.databinding.ViewThumbsBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme

class ThumbsQuestion(theme: SurveyTheme) : QuestionWidget<ViewThumbsBinding>(theme) {
    @LayoutRes
    override val layoutId: Int = layout.view_thumbs

    private lateinit var viewBinding: ViewThumbsBinding

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): ViewThumbsBinding {
        viewBinding = ViewThumbsBinding.bind(inflateWidget(parent = parent)).apply {
            val thumbs = listOf(thumbsUp, thumbsDown)
            // set them all to "selected" state to start
            thumbs.forEach { it.isChecked = true }
            // apply theme
            theme.applyDltTheme { applyBaseIconStateful(views = thumbs) }

            thumbsUp.setOnClickListener {
                thumbsUp.isChecked = true
                thumbsDown.isChecked = false
                setAnswerValue(1)
            }
            thumbsDown.setOnClickListener {
                thumbsUp.isChecked = false
                thumbsDown.isChecked = true
                setAnswerValue(0)
            }
        }
        return viewBinding
    }

    override fun getWidget() = viewBinding
}
