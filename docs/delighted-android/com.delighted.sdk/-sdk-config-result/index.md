//[delighted-android](../../../index.md)/[com.delighted.sdk](../index.md)/[SdkConfigResult](index.md)

# SdkConfigResult

[androidJvm]\
sealed class [SdkConfigResult](index.md)

## Types

| Name | Summary |
|---|---|
| [SdkConfigFail](-sdk-config-fail/index.md) | [androidJvm]<br>data class [SdkConfigFail](-sdk-config-fail/index.md)(val throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?) : [SdkConfigResult](index.md) |
| [SdkConfigSuccess](-sdk-config-success/index.md) | [androidJvm]<br>data class [SdkConfigSuccess](-sdk-config-success/index.md)(val sdkConfiguration: [SdkConfiguration](../../com.delighted.sdk.domain/-sdk-configuration/index.md)) : [SdkConfigResult](index.md) |

## Inheritors

| Name |
|---|
| [SdkConfigFail](-sdk-config-fail/index.md) |
| [SdkConfigSuccess](-sdk-config-success/index.md) |
