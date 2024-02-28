//[delighted-android](../../index.md)/[com.delighted.sdk.interactor](index.md)

# Package com.delighted.sdk.interactor

## Types

| Name | Summary |
|---|---|
| [ClientEligibilityCheckUseCase](-client-eligibility-check-use-case/index.md) | [androidJvm]<br>class [ClientEligibilityCheckUseCase](-client-eligibility-check-use-case/index.md) : [UseCase](-use-case/index.md)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [ClientEligibilityCheckUseCase.ClientEligibilityCheckResult](-client-eligibility-check-use-case/-client-eligibility-check-result/index.md)&gt; |
| [FlowUseCase](-flow-use-case/index.md) | [androidJvm]<br>interface [FlowUseCase](-flow-use-case/index.md)&lt;[T](-flow-use-case/index.md), [R](-flow-use-case/index.md)&gt; : [UseCase](-use-case/index.md)&lt;[T](-flow-use-case/index.md), Flow&lt;[R](-flow-use-case/index.md)&gt;&gt; <br>An interface representing an executable operation that returns a Flow. |
| [SuspendableUseCase](-suspendable-use-case/index.md) | [androidJvm]<br>interface [SuspendableUseCase](-suspendable-use-case/index.md)&lt;[T](-suspendable-use-case/index.md), [R](-suspendable-use-case/index.md)&gt;<br>An interface representing a suspendable, executable operation. |
| [UseCase](-use-case/index.md) | [androidJvm]<br>interface [UseCase](-use-case/index.md)&lt;in [T](-use-case/index.md), out [R](-use-case/index.md)&gt;<br>An interface representing a synchronous executable operation. |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>suspend operator fun &lt;[R](invoke.md)&gt; [SuspendableUseCase](-suspendable-use-case/index.md)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)&gt;.[invoke](invoke.md)(): [R](invoke.md)<br>operator fun &lt;[R](invoke.md)&gt; [UseCase](-use-case/index.md)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)&gt;.[invoke](invoke.md)(): [R](invoke.md)<br>suspend operator fun &lt;[T](invoke.md), [R](invoke.md)&gt; [SuspendableUseCase](-suspendable-use-case/index.md)&lt;[T](invoke.md), [R](invoke.md)&gt;.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md)<br>operator fun &lt;[T](invoke.md), [R](invoke.md)&gt; [UseCase](-use-case/index.md)&lt;[T](invoke.md), [R](invoke.md)&gt;.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md) |
