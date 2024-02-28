//[android-template-v2](../../index.md)/[com.rightpoint.template.data.utils](index.md)/[networkBoundResource](network-bound-resource.md)

# networkBoundResource

[androidJvm]\
inline fun <[RemoteType](network-bound-resource.md), [CachedType](network-bound-resource.md)> [networkBoundResource](network-bound-resource.md)(crossinline query: () -> Flow<[CachedType](network-bound-resource.md)>, crossinline fetch: suspend () -> [RemoteType](network-bound-resource.md), crossinline save: suspend ([RemoteType](network-bound-resource.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), crossinline shouldFetch: ([CachedType](network-bound-resource.md)?) -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): Flow<[Resource](-resource/index.md)<[CachedType](network-bound-resource.md)>>
