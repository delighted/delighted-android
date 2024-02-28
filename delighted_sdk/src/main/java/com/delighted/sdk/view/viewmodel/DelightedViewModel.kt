package com.delighted.sdk.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delighted.sdk.AppDispatchers
import com.delighted.sdk.core.scope
import com.delighted.sdk.interactor.GetSurveyStateUseCase
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.AdditionalQuestions
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ClientEligibilityPassed
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ConnectingPusher
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.FirstQuestion
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.NotActive
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState.ThankYou
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.Finish
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowAdditionalQuestions
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowFirstQuestion
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowThankYou
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DelightedViewModel @Inject constructor(
    application: Application,
    private val getSurveyStateUseCase: GetSurveyStateUseCase,
    dispatchers: AppDispatchers,
) : AndroidViewModel(application) {
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        dispatchers.mainThread.scope().launch {
            getSurveyStateUseCase().collectLatest { surveyState ->
                when (surveyState) {
                    ThankYou -> _viewState.value = ShowThankYou
                    AdditionalQuestions -> _viewState.value = ShowAdditionalQuestions
                    NotActive -> {
                        _viewState.value = Finish
                        this.cancel()
                    }
                    is ClientEligibilityPassed -> {}
                    is FirstQuestion -> _viewState.value = ShowFirstQuestion
                    ConnectingPusher -> {}
                }
            }
        }
    }

    sealed class ViewState {
        object ShowFirstQuestion : ViewState()
        object ShowAdditionalQuestions : ViewState()
        object ShowThankYou : ViewState()
        object Finish : ViewState()
    }
}
