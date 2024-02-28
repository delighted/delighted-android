package com.delighted.sdk.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delighted.sdk.AppDispatchers
import com.delighted.sdk.core.scope
import com.delighted.sdk.core.setIfDistinct
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.domain.response.SurveyTypeIdentifier
import com.delighted.sdk.interactor.CompleteFirstQuestionUseCase
import com.delighted.sdk.interactor.FirstQuestionPromptUseCase
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions
import com.delighted.sdk.interactor.GetCommentPromptUseCase
import com.delighted.sdk.interactor.GetPoweredByUseCase
import com.delighted.sdk.interactor.GetPoweredByUseCase.PoweredBy
import com.delighted.sdk.interactor.GetPrimaryButtonLabelUseCase
import com.delighted.sdk.interactor.GetScoreTextsUseCase
import com.delighted.sdk.interactor.GetSurveyDisplayUseCase
import com.delighted.sdk.interactor.GetSurveyDisplayUseCase.SurveyDisplay
import com.delighted.sdk.interactor.GetSurveyThemeUseCase
import com.delighted.sdk.interactor.GetSurveyTypeUseCase
import com.delighted.sdk.interactor.KillSdkUseCase
import com.delighted.sdk.interactor.NotifyUserActivityUseCase
import com.delighted.sdk.interactor.StoreAndSendFirstQuestionAnswerUseCase
import com.delighted.sdk.interactor.StoreAndSendFirstQuestionAnswerUseCase.Params
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.view.viewmodel.StartSurveyViewModel.ViewState.Expanded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StartSurveyViewModel @Inject constructor(
    application: Application,
    private val completeFirstQuestionUseCase: CompleteFirstQuestionUseCase,
    private val getCommentPromptUseCase: GetCommentPromptUseCase,
    private val getPrimaryButtonLabelUseCase: GetPrimaryButtonLabelUseCase,
    private val getScoreTextsUseCase: GetScoreTextsUseCase,
    private val firstQuestionPromptUseCase: FirstQuestionPromptUseCase,
    private val firstQuestionTextOptionsUseCase: FirstQuestionTextOptionsUseCase,
    private val getPoweredByUseCase: GetPoweredByUseCase,
    private val getSurveyThemeUseCase: GetSurveyThemeUseCase,
    private val getSurveyDisplayUseCase: GetSurveyDisplayUseCase,
    private val getSurveyTypeUseCase: GetSurveyTypeUseCase,
    private val killSdk: KillSdkUseCase,
    private val notifyUserActivityUseCase: NotifyUserActivityUseCase,
    private val storeAndSendFirstQuestionAnswerUseCase: StoreAndSendFirstQuestionAnswerUseCase,
    private val dispatchers: AppDispatchers,
) : AndroidViewModel(application) {
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private val _commentPrompt = MutableLiveData<String>()
    val commentPrompt: LiveData<String> = _commentPrompt

    val surveyType: SurveyTypeIdentifier get() = getSurveyTypeUseCase()
    val surveyTheme: SurveyTheme get() = getSurveyThemeUseCase()
    val firstQuestionPrompt: String? get() = firstQuestionPromptUseCase()
    val firstQuestionTextOptions: FirstQuestionTextOptions get() = firstQuestionTextOptionsUseCase()
    val shouldUseSlider: Boolean
        get() = getScoreTextsUseCase()?.let { it.size > MAX_OPTIONS_FOR_BUTTONS } ?: false
    val choiceIndexes: List<Int>
        get() = getScoreTextsUseCase()?.keys?.toList().orEmpty()
    val actionButtonLabel: String get() = getPrimaryButtonLabelUseCase()
    val poweredByTextAndUrl: PoweredBy get() = getPoweredByUseCase()
    val modalOrCard: SurveyDisplay get() = getSurveyDisplayUseCase()

    fun answerFirstQuestion(index: Int? = null, comment: String? = null) {
        dispatchers.mainThread.scope().launch {
            storeAndSendFirstQuestionAnswerUseCase(
                params = Params(
                    score = index,
                    comment = comment
                )
            )
        }
        _viewState setIfDistinct Expanded
        index?.let { _commentPrompt.value = getCommentPromptUseCase()?.get(it) }
    }

    fun completeFirstQuestion() = completeFirstQuestionUseCase()
    fun close() = killSdk()
    fun notifyUserActivity() = notifyUserActivityUseCase()

    sealed class ViewState {
        object Expanded : ViewState()
    }

    companion object {
        const val MAX_OPTIONS_FOR_BUTTONS = 6
    }
}
