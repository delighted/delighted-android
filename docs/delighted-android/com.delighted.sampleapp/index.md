//[delighted-android](../../index.md)/[com.delighted.sampleapp](index.md)

# Package com.delighted.sampleapp

## Types

| Name | Summary |
|---|---|
| [AppConfig](-app-config/index.md) | [androidJvm]<br>interface [AppConfig](-app-config/index.md)<br>Replacement for the auto-generated BuildConfig class. Contains all of the usual values provided by the BuildConfig class, as well as some additional values, such as the hash of the latest git commit and the commit timestamp. |
| [AppModule](-app-module/index.md) | [androidJvm]<br>@Module<br>abstract class [AppModule](-app-module/index.md) |
| [ButtonViewHolder](-button-view-holder/index.md) | [androidJvm]<br>class [ButtonViewHolder](-button-view-holder/index.md)(viewBinding: &lt;ERROR CLASS&gt;, callback: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html) |
| [DelightedSampleApp](-delighted-sample-app/index.md) | [androidJvm]<br>class [DelightedSampleApp](-delighted-sample-app/index.md) : [Application](https://developer.android.com/reference/kotlin/android/app/Application.html) |
| [DelightedSampleAppConfig](-delighted-sample-app-config/index.md) | [androidJvm]<br>@Singleton<br>class [DelightedSampleAppConfig](-delighted-sample-app-config/index.md)@Injectconstructor : [AppConfig](-app-config/index.md) |
| [GitHash](-git-hash/index.md) | [androidJvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [GitHash](-git-hash/index.md)(val hash: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [MainActivity](-main-activity/index.md) | [androidJvm]<br>class [MainActivity](-main-activity/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html) |
| [MainViewModel](-main-view-model/index.md) | [androidJvm]<br>class [MainViewModel](-main-view-model/index.md)@Injectconstructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), val surveySdk: [SurveySdk](../com.delighted.sdk/-survey-sdk/index.md)) : [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) |
| [SurveyButtonsAdapter](-survey-buttons-adapter/index.md) | [androidJvm]<br>class [SurveyButtonsAdapter](-survey-buttons-adapter/index.md) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[ButtonViewHolder](-button-view-holder/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [abbreviated](abbreviated.md) | [androidJvm]<br>fun [GitHash](-git-hash/index.md).[abbreviated](abbreviated.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
