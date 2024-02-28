//[android-template-v2](../index.md)/[com.rightpoint.template.domain.interactor](index.md)



# Package com.rightpoint.template.domain.interactor


## Types

|  Name|  Summary|
|---|---|
| <a name="com.rightpoint.template.domain.interactor/FlowUseCase///PointingToDeclaration/"></a>[FlowUseCase](-flow-use-case/index.md)| <a name="com.rightpoint.template.domain.interactor/FlowUseCase///PointingToDeclaration/"></a>[jvm]  <br>Content  <br>interface [FlowUseCase](-flow-use-case/index.md)<[T](-flow-use-case/index.md), [R](-flow-use-case/index.md)> : [UseCase](-use-case/index.md)<[T](-flow-use-case/index.md), Flow<[R](-flow-use-case/index.md)>>   <br>More info  <br>An interface representing an executable operation that returns a Flow.  <br><br><br>
| <a name="com.rightpoint.template.domain.interactor/SuspendableUseCase///PointingToDeclaration/"></a>[SuspendableUseCase](-suspendable-use-case/index.md)| <a name="com.rightpoint.template.domain.interactor/SuspendableUseCase///PointingToDeclaration/"></a>[jvm]  <br>Content  <br>interface [SuspendableUseCase](-suspendable-use-case/index.md)<[T](-suspendable-use-case/index.md), [R](-suspendable-use-case/index.md)>  <br>More info  <br>An interface representing a suspendable, executable operation.  <br><br><br>
| <a name="com.rightpoint.template.domain.interactor/UseCase///PointingToDeclaration/"></a>[UseCase](-use-case/index.md)| <a name="com.rightpoint.template.domain.interactor/UseCase///PointingToDeclaration/"></a>[jvm]  <br>Content  <br>interface [UseCase](-use-case/index.md)<in [T](-use-case/index.md), out [R](-use-case/index.md)>  <br>More info  <br>An interface representing a synchronous executable operation.  <br><br><br>


## Functions

|  Name|  Summary|
|---|---|
| <a name="com.rightpoint.template.domain.interactor//invoke/com.rightpoint.template.domain.interactor.SuspendableUseCase[kotlin.Unit,TypeParam(bounds=[kotlin.Any?])]#/PointingToDeclaration/"></a>[invoke](invoke.md)| <a name="com.rightpoint.template.domain.interactor//invoke/com.rightpoint.template.domain.interactor.SuspendableUseCase[kotlin.Unit,TypeParam(bounds=[kotlin.Any?])]#/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>suspend operator fun <[R](invoke.md)> [SuspendableUseCase](-suspendable-use-case/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)>.[invoke](invoke.md)(): [R](invoke.md)  <br>operator fun <[R](invoke.md)> [UseCase](-use-case/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](invoke.md)>.[invoke](invoke.md)(): [R](invoke.md)  <br>suspend operator fun <[T](invoke.md), [R](invoke.md)> [SuspendableUseCase](-suspendable-use-case/index.md)<[T](invoke.md), [R](invoke.md)>.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md)  <br>operator fun <[T](invoke.md), [R](invoke.md)> [UseCase](-use-case/index.md)<[T](invoke.md), [R](invoke.md)>.[invoke](invoke.md)(params: [T](invoke.md)): [R](invoke.md)  <br><br><br>
