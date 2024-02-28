package com.delighted.sdk

import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.domain.request.Person

/**
 * Initial parameters that must be passed in by the host app to begin interaction with the SDK
 */
data class SdkInitParams(
    val delightedId: String,
    val person: Person? = null,
    val properties: Map<String, String>? = null,
    val token: String? = null,
    val createdAt: Long? = null,
    val initialDelay: Int? = null,
    val recurringPeriod: Int? = null,
    val themeOverride: SurveyTheme? = null,
    val testMode: Boolean = false,
    val serverUrl: String? = null
)
