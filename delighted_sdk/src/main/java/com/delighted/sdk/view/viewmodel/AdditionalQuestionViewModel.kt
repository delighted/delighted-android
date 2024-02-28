package com.delighted.sdk.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delighted.sdk.AppDispatchers
import com.delighted.sdk.core.scope
import com.delighted.sdk.domain.response.AdditionQuestionOptionResponse
import com.delighted.sdk.domain.response.AdditionalQuestionResponse
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType
import com.delighted.sdk.interactor.GetPoweredByUseCase
import com.delighted.sdk.interactor.GetSecondaryButtonLabelUseCase
import com.delighted.sdk.interactor.GetSurveyThemeUseCase
import com.delighted.sdk.interactor.KillSdkUseCase
import com.delighted.sdk.interactor.aq.AnswerParams
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerFreeForm
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerScale
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerSelect
import com.delighted.sdk.interactor.aq.GetAdditionalQuestionUseCase
import com.delighted.sdk.interactor.aq.GetAqAnswerUseCase
import com.delighted.sdk.interactor.aq.GetAqIdByIndexUseCase
import com.delighted.sdk.interactor.aq.GetAqInstructionTextUseCase
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.FirstAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.LastAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.OnlyAq
import com.delighted.sdk.interactor.aq.GetAqPrimaryButtonLabelUseCase
import com.delighted.sdk.interactor.aq.GetAqScaleSettingsUseCase
import com.delighted.sdk.interactor.aq.GetAqScaleSettingsUseCase.AqScaleSettings
import com.delighted.sdk.interactor.aq.SendAqAnswerUseCase
import com.delighted.sdk.interactor.aq.StoreAqAnswerUseCase
import com.delighted.sdk.interactor.invoke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AdditionalQuestionViewModel @Inject constructor(
    application: Application,
    private val getAqScaleSettingsUseCase: GetAqScaleSettingsUseCase,
    private val getAdditionalQuestionUseCase: GetAdditionalQuestionUseCase,
    private val getAqIdByIndexUseCase: GetAqIdByIndexUseCase,
    private val getAqPrimaryButtonLabelUseCase: GetAqPrimaryButtonLabelUseCase,
    private val getAqInstructionTextUseCase: GetAqInstructionTextUseCase,
    private val getSecondaryButtonLabelUseCase: GetSecondaryButtonLabelUseCase,
    private val getPoweredByUseCase: GetPoweredByUseCase,
    private val getSurveyThemeUseCase: GetSurveyThemeUseCase,
    private val getAqPositionUseCase: GetAqPositionUseCase,
    private val killSdk: KillSdkUseCase,
    private val storeAqAnswerUseCase: StoreAqAnswerUseCase,
    private val sendAqAnswerUseCase: SendAqAnswerUseCase,
    private val getAqAnswerUseCase: GetAqAnswerUseCase,
    private val dispatchers: AppDispatchers,
) : AndroidViewModel(application) {

    private val _questionIndex = MutableLiveData(0)
    private val index get() = _questionIndex.value!!
    val questionIndex: LiveData<Int> get() = _questionIndex

    private val additionalQuestion: AdditionalQuestionResponse?
        get() = getAdditionalQuestionUseCase(
            params = aqId
        )
    private val aqId get() = getAqIdByIndexUseCase(params = index)

    // properties depending on a specific aq
    val questionType: AdditionalQuestionType? get() = additionalQuestion?.type
    val questionText: String get() = additionalQuestion?.text.orEmpty()
    val questionOptions: List<AdditionQuestionOptionResponse> get() = additionalQuestion?.options.orEmpty()
    val questionInstructionText: String? get() = getAqInstructionTextUseCase(params = aqId)
    val scaleQuestionSettings: AqScaleSettings get() = getAqScaleSettingsUseCase(params = aqId)
    val shouldUseSlider: Boolean
        get() = getAqScaleSettingsUseCase(params = aqId).let {
            (it.scaleMax - it.scaleMin + 1) > StartSurveyViewModel.MAX_OPTIONS_FOR_BUTTONS
        }
    val primaryButtonText get() = getAqPrimaryButtonLabelUseCase(params = aqId)

    // does not depend on specific aq
    val surveyTheme get() = getSurveyThemeUseCase()
    val secondaryButtonText get() = getSecondaryButtonLabelUseCase()
    val poweredByTextAndUrl get() = getPoweredByUseCase()

    /**
     * Returns true if there is another next question. If true, increments the current question
     * index
     */
    fun nextQuestion(): Boolean {
        val questionId = aqId // capture value before async stuff
        dispatchers.mainThread.scope().launch {
            sendAqAnswerUseCase(questionId)
        }
        val hasNext = when (getAqPositionUseCase(params = aqId)) {
            LastAq, OnlyAq -> false
            else -> true
        }
        if (hasNext) _questionIndex.postValue(index + 1)
        return hasNext
    }

    /**
     * Returns true if there is a previous question. If true, decrements the current question
     * index
     */
    fun previousQuestion(): Boolean {
        val questionId = aqId // capture value before async stuff
        dispatchers.mainThread.scope().launch {
            sendAqAnswerUseCase(questionId)
        }
        val hasPrevious = hasPreviousQuestion()
        if (hasPrevious) _questionIndex.postValue(index - 1)
        return hasPrevious
    }

    fun showPreviousButton() = hasPreviousQuestion()

    fun close() = killSdk()

    fun setAnswer(answers: List<String>) {
        storeAqAnswerUseCase(
            params = AnswerSelect(
                list = answers,
                questionId = aqId
            )
        )
    }

    fun setAnswer(value: String) {
        storeAqAnswerUseCase(
            params = AnswerFreeForm(
                content = value,
                questionId = aqId
            )
        )
    }

    fun setAnswer(value: Int) {
        storeAqAnswerUseCase(
            params = AnswerScale(
                value = value,
                questionId = aqId
            )
        )
    }

    fun getAnswer(): AnswerParams? = getAqAnswerUseCase(params = aqId)

    private fun hasPreviousQuestion(): Boolean = when (getAqPositionUseCase(params = aqId)) {
        FirstAq, OnlyAq -> false
        else -> true
    }
}
