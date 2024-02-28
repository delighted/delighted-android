//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.request](../index.md)/[Person](index.md)

# Person

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [Person](index.md)(@Json(name = &quot;name&quot;)val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;email&quot;)val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;phone_number&quot;)val phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

Represents a person with attributes

## Parameters

androidJvm

| | |
|---|---|
| phoneNumber | needs to be in E.164 format |

## Constructors

| | |
|---|---|
| [Person](-person.md) | [androidJvm]<br>fun [Person](-person.md)(@Json(name = &quot;name&quot;)name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;email&quot;)email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Json(name = &quot;phone_number&quot;)phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>val [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [phoneNumber](phone-number.md) | [androidJvm]<br>val [phoneNumber](phone-number.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
