//[delighted-android](../../index.md)/[com.delighted.sdk](index.md)

# Package com.delighted.sdk

## Types

| Name | Summary |
|---|---|
| [AppDispatchers](-app-dispatchers/index.md) | [androidJvm]<br>interface [AppDispatchers](-app-dispatchers/index.md) |
| [LocalCache](-local-cache/index.md) | [androidJvm]<br>class [LocalCache](-local-cache/index.md) |
| [SdkConfigResult](-sdk-config-result/index.md) | [androidJvm]<br>sealed class [SdkConfigResult](-sdk-config-result/index.md) |
| [SdkInitParams](-sdk-init-params/index.md) | [androidJvm]<br>data class [SdkInitParams](-sdk-init-params/index.md)(val delightedId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val person: [Person](../com.delighted.sdk.domain.request/-person/index.md)? = null, val properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null, val token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val createdAt: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, val initialDelay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val recurringPeriod: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val themeOverride: [SurveyTheme](../com.delighted.sdk.domain/-survey-theme/index.md)? = null, val testMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)<br>Initial parameters that must be passed in by the host app to begin interaction with the SDK |
| [SurveyInitResult](-survey-init-result/index.md) | [androidJvm]<br>sealed class [SurveyInitResult](-survey-init-result/index.md)<br>Result states when for when the SDK tries to create a survey for the user |
| [SurveySdk](-survey-sdk/index.md) | [androidJvm]<br>class [SurveySdk](-survey-sdk/index.md)<br>The Delighted Android SDK entry point |
