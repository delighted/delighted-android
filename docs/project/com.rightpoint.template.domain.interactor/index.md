//[project](../index.md)/[com.rightpoint.template.domain.interactor](index.md)



# Package com.rightpoint.template.domain.interactor


## Types

|  Name|  Summary|
|---|---|
| [FlowUseCase](-flow-use-case/index.md)| [jvm]  <br>Brief description  <br><br><br>An interface representing an executable operation that returns a Flow.<br><br>  <br>Content  <br>interface [FlowUseCase](-flow-use-case/index.md)<[T](-flow-use-case/index.md), [R](-flow-use-case/index.md)> : [UseCase](-use-case/index.md)<[T](-flow-use-case/index.md), Flow<[R](-flow-use-case/index.md)>>   <br><br><br>
| [SuspendableUseCase](-suspendable-use-case/index.md)| [jvm]  <br>Brief description  <br><br><br>An interface representing a suspendable, executable operation.<br><br>  <br>Content  <br>interface [SuspendableUseCase](-suspendable-use-case/index.md)<[T](-suspendable-use-case/index.md), [R](-suspendable-use-case/index.md)>  <br><br><br>
| [UseCase](-use-case/index.md)| [jvm]  <br>Brief description  <br><br><br>An interface representing a synchronous executable operation.<br><br>  <br>Content  <br>interface [UseCase](-use-case/index.md)<[T](-use-case/index.md), [R](-use-case/index.md)>  <br><br><br>


## Functions

|  Name|  Summary|
|---|---|
| [invoke](invoke.md)| [jvm]  <br>Content  <br>suspend operator fun <[R](invoke.md)> [SuspendableUseCase](-suspendable-use-case/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)>.[invoke](invoke.md)(): [R](invoke.md)  <br>operator fun <[R](invoke.md)> [UseCase](-use-case/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)>.[invoke](invoke.md)(): [R](invoke.md)  <br>suspend operator fun <[T](invoke.md), [R](invoke.md)> [SuspendableUseCase](-suspendable-use-case/index.md)<[T](invoke.md), [R](invoke.md)>.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md)  <br>operator fun <[T](invoke.md), [R](invoke.md)> [UseCase](-use-case/index.md)<[T](invoke.md), [R](invoke.md)>.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md)  <br><br><br>
