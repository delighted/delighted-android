//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.response](../index.md)/[SdkConfigurationResponse](index.md)

# SdkConfigurationResponse

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [SdkConfigurationResponse](index.md)(@Json(name = &quot;enabled&quot;)val enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;android_enabled&quot;)val androidEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;sample_factor&quot;)val sampleFactor: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), @Json(name = &quot;recurring_survey_period&quot;)val recurringSurveyPeriod: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, @Json(name = &quot;min_survey_interval&quot;)val minSurveyInterval: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Json(name = &quot;initial_survey_delay&quot;)val initialSurveyDelay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Json(name = &quot;plan_limit_exhausted&quot;)val planLimitExhausted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;force_display&quot;)val forceDisplay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Survey configuration information. Response object.

## Constructors

| | |
|---|---|
| [SdkConfigurationResponse](-sdk-configuration-response.md) | [androidJvm]<br>fun [SdkConfigurationResponse](-sdk-configuration-response.md)(@Json(name = &quot;enabled&quot;)enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;android_enabled&quot;)androidEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;sample_factor&quot;)sampleFactor: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), @Json(name = &quot;recurring_survey_period&quot;)recurringSurveyPeriod: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, @Json(name = &quot;min_survey_interval&quot;)minSurveyInterval: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Json(name = &quot;initial_survey_delay&quot;)initialSurveyDelay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Json(name = &quot;plan_limit_exhausted&quot;)planLimitExhausted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), @Json(name = &quot;force_display&quot;)forceDisplay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [androidEnabled](android-enabled.md) | [androidJvm]<br>val [androidEnabled](android-enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [enabled](enabled.md) | [androidJvm]<br>val [enabled](enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [forceDisplay](force-display.md) | [androidJvm]<br>val [forceDisplay](force-display.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [initialSurveyDelay](initial-survey-delay.md) | [androidJvm]<br>val [initialSurveyDelay](initial-survey-delay.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [minSurveyInterval](min-survey-interval.md) | [androidJvm]<br>val [minSurveyInterval](min-survey-interval.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [planLimitExhausted](plan-limit-exhausted.md) | [androidJvm]<br>val [planLimitExhausted](plan-limit-exhausted.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [recurringSurveyPeriod](recurring-survey-period.md) | [androidJvm]<br>val [recurringSurveyPeriod](recurring-survey-period.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? |
| [sampleFactor](sample-factor.md) | [androidJvm]<br>val [sampleFactor](sample-factor.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
