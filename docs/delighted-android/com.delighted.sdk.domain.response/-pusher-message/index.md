//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.response](../index.md)/[PusherMessage](index.md)

# PusherMessage

[androidJvm]\
sealed class [PusherMessage](index.md)

## Types

| Name | Summary |
|---|---|
| [ClientTyping](-client-typing/index.md) | [androidJvm]<br>data class [ClientTyping](-client-typing/index.md)(val channelName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val surveyRequestToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [PusherMessage](index.md) |
| [ConnectionEstablished](-connection-established/index.md) | [androidJvm]<br>class [ConnectionEstablished](-connection-established/index.md)(channelName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : [PusherMessage](index.md) |
| [Error](-error/index.md) | [androidJvm]<br>class [Error](-error/index.md)(channelName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : [PusherMessage](index.md) |
| [Subscribe](-subscribe/index.md) | [androidJvm]<br>data class [Subscribe](-subscribe/index.md)(val authToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val channelName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [PusherMessage](index.md) |
| [SubscriptionSucceeded](-subscription-succeeded/index.md) | [androidJvm]<br>class [SubscriptionSucceeded](-subscription-succeeded/index.md)(val data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : [PusherMessage](index.md) |
| [Unknown](-unknown/index.md) | [androidJvm]<br>class [Unknown](-unknown/index.md)(val event: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), channelName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : [PusherMessage](index.md) |

## Properties

| Name | Summary |
|---|---|
| [channel](channel.md) | [androidJvm]<br>val [channel](channel.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [data](data.md) | [androidJvm]<br>val [data](data.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [event](event.md) | [androidJvm]<br>val [event](event.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Subscribe](-subscribe/index.md) |
| [ClientTyping](-client-typing/index.md) |
| [ConnectionEstablished](-connection-established/index.md) |
| [Error](-error/index.md) |
| [SubscriptionSucceeded](-subscription-succeeded/index.md) |
| [Unknown](-unknown/index.md) |
