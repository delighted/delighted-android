//[delighted-android](../../../index.md)/[com.delighted.sdk.interactor.aq](../index.md)/[AnswerParams](index.md)

# AnswerParams

[androidJvm]\
sealed class [AnswerParams](index.md)

## Types

| Name | Summary |
|---|---|
| [AnswerFreeForm](-answer-free-form/index.md) | [androidJvm]<br>data class [AnswerFreeForm](-answer-free-form/index.md)(val content: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val questionId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AnswerParams](index.md) |
| [AnswerScale](-answer-scale/index.md) | [androidJvm]<br>data class [AnswerScale](-answer-scale/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val questionId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AnswerParams](index.md) |
| [AnswerSelect](-answer-select/index.md) | [androidJvm]<br>data class [AnswerSelect](-answer-select/index.md)(val list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val questionId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AnswerParams](index.md) |

## Properties

| Name | Summary |
|---|---|
| [questionId](question-id.md) | [androidJvm]<br>abstract val [questionId](question-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [AnswerFreeForm](-answer-free-form/index.md) |
| [AnswerScale](-answer-scale/index.md) |
| [AnswerSelect](-answer-select/index.md) |
