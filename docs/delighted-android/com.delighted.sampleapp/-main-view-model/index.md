//[delighted-android](../../../index.md)/[com.delighted.sampleapp](../index.md)/[MainViewModel](index.md)

# MainViewModel

[androidJvm]\
class [MainViewModel](index.md)@Injectconstructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), val surveySdk: [SurveySdk](../../com.delighted.sdk/-survey-sdk/index.md)) : [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)

## Constructors

| | |
|---|---|
| [MainViewModel](-main-view-model.md) | [androidJvm]<br>@Inject<br>fun [MainViewModel](-main-view-model.md)(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), surveySdk: [SurveySdk](../../com.delighted.sdk/-survey-sdk/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-1909672370) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-1909672370)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [clear](index.md#-1936886459%2FFunctions%2F-1909672370) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>fun [clear](index.md#-1936886459%2FFunctions%2F-1909672370)() |
| [getAboutInfo](get-about-info.md) | [androidJvm]<br>fun [getAboutInfo](get-about-info.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getApplication](index.md#1696759283%2FFunctions%2F-1909672370) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>open fun &lt;[T](index.md#1696759283%2FFunctions%2F-1909672370) : [Application](https://developer.android.com/reference/kotlin/android/app/Application.html)&gt; [getApplication](index.md#1696759283%2FFunctions%2F-1909672370)(): [T](index.md#1696759283%2FFunctions%2F-1909672370) |
| [getTag](index.md#-215894976%2FFunctions%2F-1909672370) | [androidJvm]<br>open fun &lt;[T](index.md#-215894976%2FFunctions%2F-1909672370) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [getTag](index.md#-215894976%2FFunctions%2F-1909672370)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](index.md#-215894976%2FFunctions%2F-1909672370) |
| [onCleared](index.md#-1930136507%2FFunctions%2F-1909672370) | [androidJvm]<br>open fun [onCleared](index.md#-1930136507%2FFunctions%2F-1909672370)() |
| [setTagIfAbsent](index.md#-1567230750%2FFunctions%2F-1909672370) | [androidJvm]<br>open fun &lt;[T](index.md#-1567230750%2FFunctions%2F-1909672370) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [setTagIfAbsent](index.md#-1567230750%2FFunctions%2F-1909672370)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p1: [T](index.md#-1567230750%2FFunctions%2F-1909672370)): [T](index.md#-1567230750%2FFunctions%2F-1909672370) |

## Properties

| Name | Summary |
|---|---|
| [surveySdk](survey-sdk.md) | [androidJvm]<br>val [surveySdk](survey-sdk.md): [SurveySdk](../../com.delighted.sdk/-survey-sdk/index.md) |
