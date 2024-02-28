package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Survey configuration information. Response object.
 */
@JsonClass(generateAdapter = true)
data class SdkConfigurationResponse(
    @Json(name = "enabled") val enabled: Boolean, // ios enabled
    @Json(name = "android_enabled") val androidEnabled: Boolean,
    @Json(name = "sample_factor") val sampleFactor: Double,
    @Json(name = "recurring_survey_period") val recurringSurveyPeriod: Int?,
    @Json(name = "min_survey_interval") val minSurveyInterval: Int,
    @Json(name = "initial_survey_delay") val initialSurveyDelay: Int,
    @Json(name = "plan_limit_exhausted") val planLimitExhausted: Boolean,
    @Json(name = "force_display") val forceDisplay: Boolean,
)
