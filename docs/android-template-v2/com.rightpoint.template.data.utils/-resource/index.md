//[android-template-v2](../../../index.md)/[com.rightpoint.template.data.utils](../index.md)/[Resource](index.md)

# Resource

[androidJvm]\
sealed class [Resource](index.md)<out [T](index.md)>

## Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | [androidJvm]<br>data class [Failure](-failure/index.md)(**error**: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [Resource](index.md)<[Nothing](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)> |
| [Loading](-loading/index.md) | [androidJvm]<br>object [Loading](-loading/index.md) : [Resource](index.md)<[Nothing](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)> |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)<out [T](-success/index.md)>(**data**: [T](-success/index.md)) : [Resource](index.md)<[T](-success/index.md)> |

## Inheritors

| Name |
|---|
| [Resource](-loading/index.md) |
| [Resource](-success/index.md) |
| [Resource](-failure/index.md) |
