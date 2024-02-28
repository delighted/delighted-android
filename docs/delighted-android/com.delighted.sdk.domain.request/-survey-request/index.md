//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.request](../index.md)/[SurveyRequest](index.md)

# SurveyRequest

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [SurveyRequest](index.md)(@Json(name = &quot;person&quot;)val person: [Person](../-person/index.md)?, @Json(name = &quot;properties&quot;)val properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, @Json(name = &quot;token&quot;)val token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @Json(name = &quot;test_mode&quot;)val testMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = false)

## Constructors

| | |
|---|---|
| [SurveyRequest](-survey-request.md) | [androidJvm]<br>fun [SurveyRequest](-survey-request.md)(@Json(name = &quot;person&quot;)person: [Person](../-person/index.md)?, @Json(name = &quot;properties&quot;)properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?, @Json(name = &quot;token&quot;)token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @Json(name = &quot;test_mode&quot;)testMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = false) |

## Properties

| Name | Summary |
|---|---|
| [person](person.md) | [androidJvm]<br>val [person](person.md): [Person](../-person/index.md)? |
| [properties](properties.md) | [androidJvm]<br>val [properties](properties.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [testMode](test-mode.md) | [androidJvm]<br>val [testMode](test-mode.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = false |
| [token](token.md) | [androidJvm]<br>val [token](token.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
