package com.delighted.sdk.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.interactor.GetPoweredByUseCase
import com.delighted.sdk.interactor.GetPoweredByUseCase.PoweredBy
import com.delighted.sdk.interactor.GetSurveyThemeUseCase
import com.delighted.sdk.interactor.GetThankYouMessageUseCase
import com.delighted.sdk.interactor.KillSdkUseCase
import com.delighted.sdk.interactor.invoke
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ThankYouViewModel @Inject constructor(
    application: Application,
    private val getThankYouMessageUseCase: GetThankYouMessageUseCase,
    private val getSurveyThemeUseCase: GetSurveyThemeUseCase,
    private val getPoweredByUseCase: GetPoweredByUseCase,
    private val killSdk: KillSdkUseCase,
) : AndroidViewModel(application) {

    private val thankYouMessage by lazy { getThankYouMessageUseCase() }

    val thankYouText: String get() = thankYouMessage.baseMessage
    val additionalMessage: String? get() = thankYouMessage.additionalMessage
    val autoCloseDelay: Int? get() = thankYouMessage.autoCloseDelay
    val linkTextAndUrl: Pair<String, String>? get() = thankYouMessage.linkTextAndUrl
    val surveyTheme: SurveyTheme get() = getSurveyThemeUseCase()
    val poweredByTextAndUrl: PoweredBy get() = getPoweredByUseCase()

    fun close() = killSdk()
}
