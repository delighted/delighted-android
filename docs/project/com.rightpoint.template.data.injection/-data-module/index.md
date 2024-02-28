//[project](../../index.md)/[com.rightpoint.template.data.injection](../index.md)/[DataModule](index.md)



# DataModule
[androidJvm] @Module(includes = [[DatabaseModule::class](../../com.rightpoint.template.cache.room/-database-module/index.md), [NetworkModule::class](../../com.rightpoint.template.remote/-network-module/index.md)])

object [DataModule](index.md)


## Functions

|  Name|  Summary|
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [provideDispatchers](provide-dispatchers.md)| [androidJvm]  <br>Content  <br>@Provides()  <br>  <br>fun [provideDispatchers](provide-dispatchers.md)(): [AppDispatchers](../../com.rightpoint.template.domain.dispatchers/-app-dispatchers/index.md)  <br><br><br>
| [providesSharedPreferences](provides-shared-preferences.md)| [androidJvm]  <br>Content  <br>@Provides()  <br>  <br>fun [providesSharedPreferences](provides-shared-preferences.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
