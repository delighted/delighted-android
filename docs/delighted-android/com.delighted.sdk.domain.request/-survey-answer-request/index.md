//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.request](../index.md)/[SurveyAnswerRequest](index.md)

# SurveyAnswerRequest

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [SurveyAnswerRequest](index.md)(@Json(name = &quot;survey_request_token&quot;)val surveyRequestToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;score&quot;)val score: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;comment&quot;)val comment: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;additional_questions&quot;)val additionalQuestionAnswers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionAnswer](../-additional-question-answer/index.md)&gt;? = null)

## Constructors

| | |
|---|---|
| [SurveyAnswerRequest](-survey-answer-request.md) | [androidJvm]<br>fun [SurveyAnswerRequest](-survey-answer-request.md)(@Json(name = &quot;survey_request_token&quot;)surveyRequestToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;score&quot;)score: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;comment&quot;)comment: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;additional_questions&quot;)additionalQuestionAnswers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionAnswer](../-additional-question-answer/index.md)&gt;? = null) |

## Properties

| Name | Summary |
|---|---|
| [additionalQuestionAnswers](additional-question-answers.md) | [androidJvm]<br>val [additionalQuestionAnswers](additional-question-answers.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionalQuestionAnswer](../-additional-question-answer/index.md)&gt;? = null |
| [comment](comment.md) | [androidJvm]<br>val [comment](comment.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [score](score.md) | [androidJvm]<br>val [score](score.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [surveyRequestToken](survey-request-token.md) | [androidJvm]<br>val [surveyRequestToken](survey-request-token.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
