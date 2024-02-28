//[android-template-v2](../../../index.md)/[com.rightpoint.template.data.interactor](../index.md)/[UseCase](index.md)

# UseCase

[androidJvm]\
interface [UseCase](index.md)<in [T](index.md), out [R](index.md)>

An interface representing a synchronous executable operation.

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>abstract fun [execute](execute.md)(params: [T](index.md)): [R](index.md) |

## Inheritors

| Name |
|---|
| [FlowUseCase](../-flow-use-case/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [invoke](../invoke.md) | [androidJvm]<br>operator fun <[T](../invoke.md), [R](../invoke.md)> [UseCase](index.md)<[T](../invoke.md), [R](../invoke.md)>.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)<br>operator fun <[R](../invoke.md)> [UseCase](index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)>.[invoke](../invoke.md)(): [R](../invoke.md) |
