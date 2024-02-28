//[delighted-android](../../../index.md)/[com.delighted.sdk.interactor](../index.md)/[SuspendableUseCase](index.md)

# SuspendableUseCase

[androidJvm]\
interface [SuspendableUseCase](index.md)&lt;[T](index.md), [R](index.md)&gt;

An interface representing a suspendable, executable operation.

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>abstract suspend fun [execute](execute.md)(params: [T](index.md)): [R](index.md) |

## Extensions

| Name | Summary |
|---|---|
| [invoke](../invoke.md) | [androidJvm]<br>suspend operator fun &lt;[T](../invoke.md), [R](../invoke.md)&gt; [SuspendableUseCase](index.md)&lt;[T](../invoke.md), [R](../invoke.md)&gt;.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)<br>suspend operator fun &lt;[R](../invoke.md)&gt; [SuspendableUseCase](index.md)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)&gt;.[invoke](../invoke.md)(): [R](../invoke.md) |
