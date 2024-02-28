//[project](../../index.md)/[com.rightpoint.template.domain.interactor](../index.md)/[FlowUseCase](index.md)



# FlowUseCase
[jvm]

An interface representing an executable operation that returns a Flow.

interface [FlowUseCase](index.md)<[T](index.md), [R](index.md)> : [UseCase](../-use-case/index.md)<[T](index.md), Flow<[R](index.md)>>


## Functions

|  Name|  Summary|
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [execute](../-use-case/execute.md)| [jvm]  <br>Content  <br>abstract override fun [execute](../-use-case/execute.md)(params: [T](index.md)): Flow<[R](index.md)>  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
