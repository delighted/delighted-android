//[android-template-v2](../../../index.md)/[com.rightpoint.template.remote](../index.md)/[NetworkModule](index.md)

# NetworkModule

[androidJvm]\
@Module(includes = [])

object [NetworkModule](index.md)

## Functions

| Name | Summary |
|---|---|
| [providesBaseUrl](provides-base-url.md) | [androidJvm]<br>@Provides()<br>fun [providesBaseUrl](provides-base-url.md)(): HttpUrl? |
| [providesOkHttpClient](provides-ok-http-client.md) | [androidJvm]<br>@Provides()<br>fun [providesOkHttpClient](provides-ok-http-client.md)(level: HttpLoggingInterceptor.Level): OkHttpClient |
| [providesRetrofit](provides-retrofit.md) | [androidJvm]<br>@Provides()<br>@Singleton()<br>fun [providesRetrofit](provides-retrofit.md)(url: HttpUrl?, client: Lazy<OkHttpClient>): Retrofit |
