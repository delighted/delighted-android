package com.delighted.sdk.domain

data class SdkConfiguration(
    val enabled: Boolean, // ios enabled
    val androidEnabled: Boolean,
    val sampleFactor: Double,
    val recurringSurveyPeriod: Int?,
    val minSurveyInterval: Int,
    val initialSurveyDelay: Int,
    val planLimitExhausted: Boolean,
    val forceDisplay: Boolean,
)
