//[delighted-android](../../../index.md)/[com.delighted.sdk.domain](../index.md)/[SurveyTemplate](index.md)

# SurveyTemplate

[androidJvm]\
data class [SurveyTemplate](index.md)(val questionText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val commentPrompts: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, val scoreText: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val additionalQuestions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../../com.delighted.sdk.domain.response/-additional-question-response/index.md)&gt;?)

Survey template object, with maps guaranteed to be sorted

## Constructors

| | |
|---|---|
| [SurveyTemplate](-survey-template.md) | [androidJvm]<br>fun [SurveyTemplate](-survey-template.md)(questionText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), commentPrompts: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, scoreText: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, additionalQuestions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../../com.delighted.sdk.domain.response/-additional-question-response/index.md)&gt;?) |

## Properties

| Name | Summary |
|---|---|
| [additionalQuestions](additional-questions.md) | [androidJvm]<br>val [additionalQuestions](additional-questions.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../../com.delighted.sdk.domain.response/-additional-question-response/index.md)&gt;? |
| [commentPrompts](comment-prompts.md) | [androidJvm]<br>val [commentPrompts](comment-prompts.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [questionText](question-text.md) | [androidJvm]<br>val [questionText](question-text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scoreText](score-text.md) | [androidJvm]<br>val [scoreText](score-text.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
