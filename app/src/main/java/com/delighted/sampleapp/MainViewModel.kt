package com.delighted.sampleapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.delighted.BuildConfig
import com.delighted.sdk.SurveySdk
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    val surveySdk: SurveySdk
) : AndroidViewModel(application) {
    fun getAboutInfo() = "Commit = ${BuildConfig.COMMIT_SHA.take(7)}, v ${BuildConfig.VERSION_NAME}"
}
