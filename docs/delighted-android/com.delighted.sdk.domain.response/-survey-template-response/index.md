//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.response](../index.md)/[SurveyTemplateResponse](index.md)

# SurveyTemplateResponse

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [SurveyTemplateResponse](index.md)(@Json(name = &quot;question_text&quot;)val questionText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;comment_prompts&quot;)val commentPrompts: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, @Json(name = &quot;score_text&quot;)val scoreText: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, @Json(name = &quot;additional_questions&quot;)val additionalQuestions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../-additional-question-response/index.md)&gt;?)

## Constructors

| | |
|---|---|
| [SurveyTemplateResponse](-survey-template-response.md) | [androidJvm]<br>fun [SurveyTemplateResponse](-survey-template-response.md)(@Json(name = &quot;question_text&quot;)questionText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;comment_prompts&quot;)commentPrompts: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, @Json(name = &quot;score_text&quot;)scoreText: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, @Json(name = &quot;additional_questions&quot;)additionalQuestions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../-additional-question-response/index.md)&gt;?) |

## Properties

| Name | Summary |
|---|---|
| [additionalQuestions](additional-questions.md) | [androidJvm]<br>val [additionalQuestions](additional-questions.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionResponse](../-additional-question-response/index.md)&gt;? |
| [commentPrompts](comment-prompts.md) | [androidJvm]<br>val [commentPrompts](comment-prompts.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [questionText](question-text.md) | [androidJvm]<br>val [questionText](question-text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scoreText](score-text.md) | [androidJvm]<br>val [scoreText](score-text.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
