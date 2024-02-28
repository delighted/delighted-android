//[delighted-android](../../../index.md)/[com.delighted.sdk.network](../index.md)/[NetworkFetch](index.md)

# NetworkFetch

[androidJvm]\
class [NetworkFetch](index.md)@Injectconstructor(surveyInitRepo: [SurveyInitRepository](../../com.delighted.sdk.repository/-survey-init-repository/index.md), dispatchers: [AppDispatchers](../../com.delighted.sdk/-app-dispatchers/index.md))

Android-only networking layer, to avoid external dependencies

## Constructors

| | |
|---|---|
| [NetworkFetch](-network-fetch.md) | [androidJvm]<br>@Inject<br>fun [NetworkFetch](-network-fetch.md)(surveyInitRepo: [SurveyInitRepository](../../com.delighted.sdk.repository/-survey-init-repository/index.md), dispatchers: [AppDispatchers](../../com.delighted.sdk/-app-dispatchers/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [requestPusherAuth](request-pusher-auth.md) | [androidJvm]<br>suspend fun [requestPusherAuth](request-pusher-auth.md)(pusherAuthRequestJson: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [requestSdkConfiguration](request-sdk-configuration.md) | [androidJvm]<br>suspend fun [requestSdkConfiguration](request-sdk-configuration.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [requestSurvey](request-survey.md) | [androidJvm]<br>suspend fun [requestSurvey](request-survey.md)(surveyRequestJson: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [surveyAnswer](survey-answer.md) | [androidJvm]<br>suspend fun [surveyAnswer](survey-answer.md)(surveyResponseJson: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
