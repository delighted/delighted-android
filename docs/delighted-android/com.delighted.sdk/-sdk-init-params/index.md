//[delighted-android](../../../index.md)/[com.delighted.sdk](../index.md)/[SdkInitParams](index.md)

# SdkInitParams

[androidJvm]\
data class [SdkInitParams](index.md)(val delightedId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val person: [Person](../../com.delighted.sdk.domain.request/-person/index.md)? = null, val properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null, val token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val createdAt: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, val initialDelay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val recurringPeriod: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val themeOverride: [SurveyTheme](../../com.delighted.sdk.domain/-survey-theme/index.md)? = null, val testMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

Initial parameters that must be passed in by the host app to begin interaction with the SDK

## Constructors

| | |
|---|---|
| [SdkInitParams](-sdk-init-params.md) | [androidJvm]<br>fun [SdkInitParams](-sdk-init-params.md)(delightedId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), person: [Person](../../com.delighted.sdk.domain.request/-person/index.md)? = null, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null, token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, createdAt: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, initialDelay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, recurringPeriod: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, themeOverride: [SurveyTheme](../../com.delighted.sdk.domain/-survey-theme/index.md)? = null, testMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [createdAt](created-at.md) | [androidJvm]<br>val [createdAt](created-at.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null |
| [delightedId](delighted-id.md) | [androidJvm]<br>val [delightedId](delighted-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [initialDelay](initial-delay.md) | [androidJvm]<br>val [initialDelay](initial-delay.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [person](person.md) | [androidJvm]<br>val [person](person.md): [Person](../../com.delighted.sdk.domain.request/-person/index.md)? = null |
| [properties](properties.md) | [androidJvm]<br>val [properties](properties.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null |
| [recurringPeriod](recurring-period.md) | [androidJvm]<br>val [recurringPeriod](recurring-period.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [serverUrl](server-url.md) | [androidJvm]<br>val [serverUrl](server-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [testMode](test-mode.md) | [androidJvm]<br>val [testMode](test-mode.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [themeOverride](theme-override.md) | [androidJvm]<br>val [themeOverride](theme-override.md): [SurveyTheme](../../com.delighted.sdk.domain/-survey-theme/index.md)? = null |
| [token](token.md) | [androidJvm]<br>val [token](token.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
