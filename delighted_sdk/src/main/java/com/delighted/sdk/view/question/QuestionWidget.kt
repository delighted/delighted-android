package com.delighted.sdk.view.question

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.delighted.sdk.domain.SurveyTheme

sealed class QuestionWidget<T : ViewBinding>(protected val theme: SurveyTheme) {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (answer: Int) -> Unit
    ): T

    abstract fun getWidget(): T

    protected fun inflateWidget(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        parent.addView(view)
        return view
    }
}
