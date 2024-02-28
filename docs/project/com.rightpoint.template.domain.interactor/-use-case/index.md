//[project](../../index.md)/[com.rightpoint.template.domain.interactor](../index.md)/[UseCase](index.md)



# UseCase
[jvm]

An interface representing a synchronous executable operation.

interface [UseCase](index.md)<[T](index.md), [R](index.md)>


## Functions

|  Name|  Summary|
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [execute](execute.md)| [jvm]  <br>Content  <br>abstract fun [execute](execute.md)(params: [T](index.md)): [R](index.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors

|  Name|
|---|
| [FlowUseCase](../-flow-use-case/index.md)


## Extensions

|  Name|  Summary|
|---|---|
| [invoke](../invoke.md)| [jvm]  <br>Content  <br>operator fun <[T](../invoke.md), [R](../invoke.md)> [UseCase](index.md)<[T](../invoke.md), [R](../invoke.md)>.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)  <br>operator fun <[R](../invoke.md)> [UseCase](index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)>.[invoke](../invoke.md)(): [R](../invoke.md)  <br><br><br>
