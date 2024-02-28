package com.delighted.sdk.interactor.aq

sealed class AnswerParams {
    abstract val questionId: String

    data class AnswerFreeForm(val content: String, override val questionId: String) : AnswerParams()
    data class AnswerScale(val value: Int, override val questionId: String) : AnswerParams()
    data class AnswerSelect(val list: List<String>, override val questionId: String) :
        AnswerParams()
}
