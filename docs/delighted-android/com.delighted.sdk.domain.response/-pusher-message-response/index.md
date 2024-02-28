//[delighted-android](../../../index.md)/[com.delighted.sdk.domain.response](../index.md)/[PusherMessageResponse](index.md)

# PusherMessageResponse

[androidJvm]\
@JsonClass(generateAdapter = false)

data class [PusherMessageResponse](index.md)(@Json(name = &quot;event&quot;)val event: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;data&quot;)val data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, @Json(name = &quot;channel&quot;)val channel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

## Constructors

| | |
|---|---|
| [PusherMessageResponse](-pusher-message-response.md) | [androidJvm]<br>fun [PusherMessageResponse](-pusher-message-response.md)(@Json(name = &quot;event&quot;)event: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Json(name = &quot;data&quot;)data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, @Json(name = &quot;channel&quot;)channel: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [channel](channel.md) | [androidJvm]<br>val [channel](channel.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [data](data.md) | [androidJvm]<br>val [data](data.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [event](event.md) | [androidJvm]<br>val [event](event.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
