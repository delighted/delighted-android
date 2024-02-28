//[android-template-v2](../../index.md)/[com.rightpoint.template](index.md)

# Package com.rightpoint.template

## Types

| Name | Summary |
|---|---|
| [AppConfig](-app-config/index.md) | [androidJvm]<br>interface [AppConfig](-app-config/index.md)<br>Replacement for the auto-generated BuildConfig class. |
| [AppModule](-app-module/index.md) | [androidJvm]<br>@Module()<br>abstract class [AppModule](-app-module/index.md) |
| [GitHash](-git-hash/index.md) | [androidJvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)()<br>value class [GitHash](-git-hash/index.md)(**hash**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [MainActivity](-main-activity/index.md) | [androidJvm]<br>class [MainActivity](-main-activity/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html) |
| [TemplateApp](-template-app/index.md) | [androidJvm]<br>class [TemplateApp](-template-app/index.md) : [Application](https://developer.android.com/reference/kotlin/android/app/Application.html) |
| [TemplateAppConfig](-template-app-config/index.md) | [androidJvm]<br>@Singleton()<br>class [TemplateAppConfig](-template-app-config/index.md)@Inject()constructor : [AppConfig](-app-config/index.md) |

## Functions

| Name | Summary |
|---|---|
| [abbreviated](abbreviated.md) | [androidJvm]<br>fun [GitHash](-git-hash/index.md).[abbreviated](abbreviated.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
