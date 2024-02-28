//[android-template-v2](../../index.md)/[com.rightpoint.template.domain.interactor](../index.md)/[SuspendableUseCase](index.md)



# SuspendableUseCase
[jvm] interface [SuspendableUseCase](index.md)<[T](index.md), [R](index.md)>

An interface representing a suspendable, executable operation.




## Functions

|  Name|  Summary|
|---|---|
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../-use-case/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>open operator fun [equals](../-use-case/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="com.rightpoint.template.domain.interactor/SuspendableUseCase/execute/#TypeParam(bounds=[kotlin.Any?])/PointingToDeclaration/"></a>[execute](execute.md)| <a name="com.rightpoint.template.domain.interactor/SuspendableUseCase/execute/#TypeParam(bounds=[kotlin.Any?])/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>abstract suspend fun [execute](execute.md)(params: [T](index.md)): [R](index.md)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../-use-case/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>open fun [hashCode](../-use-case/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../-use-case/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>open fun [toString](../-use-case/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F-823872895)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Extensions

|  Name|  Summary|
|---|---|
| <a name="com.rightpoint.template.domain.interactor//invoke/com.rightpoint.template.domain.interactor.SuspendableUseCase[TypeParam(bounds=[kotlin.Any?]),TypeParam(bounds=[kotlin.Any?])]#TypeParam(bounds=[kotlin.Any?])/PointingToDeclaration/"></a>[invoke](../invoke.md)| <a name="com.rightpoint.template.domain.interactor//invoke/com.rightpoint.template.domain.interactor.SuspendableUseCase[TypeParam(bounds=[kotlin.Any?]),TypeParam(bounds=[kotlin.Any?])]#TypeParam(bounds=[kotlin.Any?])/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>suspend operator fun <[T](../invoke.md), [R](../invoke.md)> [SuspendableUseCase](index.md)<[T](../invoke.md), [R](../invoke.md)>.[invoke](../invoke.md)(params: [T](../invoke.md)): [R](../invoke.md)  <br>suspend operator fun <[R](../invoke.md)> [SuspendableUseCase](index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](../invoke.md)>.[invoke](../invoke.md)(): [R](../invoke.md)  <br><br><br>
