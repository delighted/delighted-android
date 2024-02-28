//[delighted-android](../../../index.md)/[com.delighted.sdk.interactor](../index.md)/[FlowUseCase](index.md)

# FlowUseCase

[androidJvm]\
interface [FlowUseCase](index.md)&lt;[T](index.md), [R](index.md)&gt; : [UseCase](../-use-case/index.md)&lt;[T](index.md), Flow&lt;[R](index.md)&gt;&gt;

An interface representing an executable operation that returns a Flow.

## Functions

| Name | Summary |
|---|---|
| [execute](../-use-case/execute.md) | [androidJvm]<br>abstract fun [execute](../-use-case/execute.md)(params: [T](index.md)): Flow&lt;[R](index.md)&gt; |
