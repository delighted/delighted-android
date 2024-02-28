//[delighted-android](../../../index.md)/[com.delighted.sdk.interactor](../index.md)/[UseCase](index.md)

# UseCase

[androidJvm]\
interface [UseCase](index.md)&lt;in [T](index.md), out [R](index.md)&gt;

An interface representing a synchronous executable operation.

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>abstract fun [execute](execute.md)(params: [T](index.md)): [R](index.md) |

## Inheritors

| Name |
|---|
| [ClientEligibilityCheckUseCase](../-client-eligibility-check-use-case/index.md) |
| [FlowUseCase](../-flow-use-case/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [invoke](../invoke.md) | [androidJvm]<br>operator fun &lt;[T](../invoke.md), [R](../invoke.md)&gt; [UseCase](index.md)&lt;[T](../invoke.md), [R](../invoke.md)&gt;.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)<br>operator fun &lt;[R](../invoke.md)&gt; [UseCase](index.md)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)&gt;.[invoke](../invoke.md)(): [R](../invoke.md) |
