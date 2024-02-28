//[android-template-v2](../../../index.md)/[com.rightpoint.template.data.interactor](../index.md)/[SuspendableUseCase](index.md)

# SuspendableUseCase

[androidJvm]\
interface [SuspendableUseCase](index.md)<[T](index.md), [R](index.md)>

An interface representing a suspendable, executable operation.

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>abstract suspend fun [execute](execute.md)(params: [T](index.md)): [R](index.md) |

## Extensions

| Name | Summary |
|---|---|
| [invoke](../invoke.md) | [androidJvm]<br>suspend operator fun <[T](../invoke.md), [R](../invoke.md)> [SuspendableUseCase](index.md)<[T](../invoke.md), [R](../invoke.md)>.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)<br>suspend operator fun <[R](../invoke.md)> [SuspendableUseCase](index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)>.[invoke](../invoke.md)(): [R](../invoke.md) |
