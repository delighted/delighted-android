//[android-template-v2](../../../index.md)/[com.rightpoint.template](../index.md)/[AppConfig](index.md)

# AppConfig

[androidJvm]\
interface [AppConfig](index.md)

Replacement for the auto-generated BuildConfig class. Contains all of the usual values provided by the BuildConfig class, as well as some additional values, such as the hash of the latest git commit and the commit timestamp.

## Properties

| Name | Summary |
|---|---|
| [applicationId](application-id.md) | [androidJvm]<br>abstract val [applicationId](application-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [buildType](build-type.md) | [androidJvm]<br>abstract val [buildType](build-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [gitSha](git-sha.md) | [androidJvm]<br>abstract val [gitSha](git-sha.md): [GitHash](../-git-hash/index.md) |
| [gitTimestamp](git-timestamp.md) | [androidJvm]<br>abstract val [gitTimestamp](git-timestamp.md): [Instant](https://developer.android.com/reference/kotlin/java/time/Instant.html) |
| [isDebug](is-debug.md) | [androidJvm]<br>abstract val [isDebug](is-debug.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sdkInt](sdk-int.md) | [androidJvm]<br>abstract val [sdkInt](sdk-int.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [versionCode](version-code.md) | [androidJvm]<br>abstract val [versionCode](version-code.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [versionName](version-name.md) | [androidJvm]<br>abstract val [versionName](version-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [TemplateAppConfig](../-template-app-config/index.md) |
