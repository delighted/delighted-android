//[delighted-android](../../../index.md)/[com.delighted.sdk.view.question](../index.md)/[QuestionWidget](index.md)

# QuestionWidget

[androidJvm]\
sealed class [QuestionWidget](index.md)&lt;[T](index.md) : ViewBinding&gt;

## Functions

| Name | Summary |
|---|---|
| [getWidget](get-widget.md) | [androidJvm]<br>abstract fun [getWidget](get-widget.md)(): [T](index.md) |
| [setupWidget](setup-widget.md) | [androidJvm]<br>abstract fun [setupWidget](setup-widget.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), setAnswerValue: (answer: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [T](index.md) |

## Properties

| Name | Summary |
|---|---|
| [layoutId](layout-id.md) | [androidJvm]<br>@get:[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)<br>abstract val [layoutId](layout-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Inheritors

| Name |
|---|
| [Csat3ButtonsQuestion](../-csat3-buttons-question/index.md) |
| [FiveStarQuestion](../-five-star-question/index.md) |
| [NumberedButtonsQuestion](../-numbered-buttons-question/index.md) |
| [PmfQuestion](../-pmf-question/index.md) |
| [SliderQuestion](../-slider-question/index.md) |
| [SmileysQuestion](../-smileys-question/index.md) |
| [ThumbsQuestion](../-thumbs-question/index.md) |
