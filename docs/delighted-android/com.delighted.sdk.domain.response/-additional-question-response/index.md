//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.response](../index.md)/[AdditionalQuestionResponse](index.md)

# AdditionalQuestionResponse

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [AdditionalQuestionResponse](index.md)(@Json(name = &quot;id&quot;)val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;type&quot;)val type: [AdditionalQuestionResponse.AdditionalQuestionType](-additional-question-type/index.md), @Json(name = &quot;text&quot;)val text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;scale_min&quot;)val scaleMin: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;scale_min_label&quot;)val scaleMinLabel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;scale_max&quot;)val scaleMax: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;scale_max_label&quot;)val scaleMaxLabel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;options&quot;)val options: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionQuestionOptionResponse](../-addition-question-option-response/index.md)&gt; = emptyList(), @Json(name = &quot;target_audience_groups&quot;)val targetAudienceGroups: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList())

## Constructors

| | |
|---|---|
| [AdditionalQuestionResponse](-additional-question-response.md) | [androidJvm]<br>fun [AdditionalQuestionResponse](-additional-question-response.md)(@Json(name = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;type&quot;)type: [AdditionalQuestionResponse.AdditionalQuestionType](-additional-question-type/index.md), @Json(name = &quot;text&quot;)text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;scale_min&quot;)scaleMin: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;scale_min_label&quot;)scaleMinLabel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;scale_max&quot;)scaleMax: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Json(name = &quot;scale_max_label&quot;)scaleMaxLabel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;options&quot;)options: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionQuestionOptionResponse](../-addition-question-option-response/index.md)&gt; = emptyList(), @Json(name = &quot;target_audience_groups&quot;)targetAudienceGroups: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList()) |

## Types

| Name | Summary |
|---|---|
| [AdditionalQuestionType](-additional-question-type/index.md) | [androidJvm]<br>@JsonClass(generateAdapter = false)<br>enum [AdditionalQuestionType](-additional-question-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[AdditionalQuestionResponse.AdditionalQuestionType](-additional-question-type/index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [options](options.md) | [androidJvm]<br>val [options](options.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AdditionQuestionOptionResponse](../-addition-question-option-response/index.md)&gt; |
| [scaleMax](scale-max.md) | [androidJvm]<br>val [scaleMax](scale-max.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [scaleMaxLabel](scale-max-label.md) | [androidJvm]<br>val [scaleMaxLabel](scale-max-label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [scaleMin](scale-min.md) | [androidJvm]<br>val [scaleMin](scale-min.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [scaleMinLabel](scale-min-label.md) | [androidJvm]<br>val [scaleMinLabel](scale-min-label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [targetAudienceGroups](target-audience-groups.md) | [androidJvm]<br>val [targetAudienceGroups](target-audience-groups.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [text](text.md) | [androidJvm]<br>val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [type](type.md) | [androidJvm]<br>val [type](type.md): [AdditionalQuestionResponse.AdditionalQuestionType](-additional-question-type/index.md) |
