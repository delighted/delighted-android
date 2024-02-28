//[project](../../index.md)/[com.rightpoint.template.remote](../index.md)/[NetworkModule](index.md)



# NetworkModule
[androidJvm] @Module(includes = [])

object [NetworkModule](index.md)


## Functions

|  Name|  Summary|
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [providesBaseUrl](provides-base-url.md)| [androidJvm]  <br>Content  <br>@Provides()  <br>  <br>fun [providesBaseUrl](provides-base-url.md)(): HttpUrl?  <br><br><br>
| [providesOkHttpClient](provides-ok-http-client.md)| [androidJvm]  <br>Content  <br>@Provides()  <br>  <br>fun [providesOkHttpClient](provides-ok-http-client.md)(level: HttpLoggingInterceptor.Level): OkHttpClient  <br><br><br>
| [providesRetrofit](provides-retrofit.md)| [androidJvm]  <br>Content  <br>@Provides()  <br>@Singleton()  <br>  <br>fun [providesRetrofit](provides-retrofit.md)(url: HttpUrl?, client: Lazy<OkHttpClient>): Retrofit  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
