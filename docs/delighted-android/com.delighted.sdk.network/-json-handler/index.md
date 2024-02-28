//[delighted-android](../../../index.md)/[com.delighted.sdk.network](../index.md)/[JsonHandler](index.md)

# JsonHandler

[androidJvm]\
class [JsonHandler](index.md)@Injectconstructor

## Constructors

| | |
|---|---|
| [JsonHandler](-json-handler.md) | [androidJvm]<br>@Inject<br>fun [JsonHandler](-json-handler.md)() |

## Functions

| Name | Summary |
|---|---|
| [attemptDecodePusherAuthResponse](attempt-decode-pusher-auth-response.md) | [androidJvm]<br>fun [attemptDecodePusherAuthResponse](attempt-decode-pusher-auth-response.md)(rawJson: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[PusherAuthResponse](../../com.delighted.sdk.domain.response/-pusher-auth-response/index.md)&gt; |
| [attemptDecodePusherMessage](attempt-decode-pusher-message.md) | [androidJvm]<br>fun [attemptDecodePusherMessage](attempt-decode-pusher-message.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[PusherMessageResponse](../../com.delighted.sdk.domain.response/-pusher-message-response/index.md)&gt; |
| [attemptDecodeSurveyConfig](attempt-decode-survey-config.md) | [androidJvm]<br>fun [attemptDecodeSurveyConfig](attempt-decode-survey-config.md)(rawJson: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SdkConfigurationResponse](../../com.delighted.sdk.domain.response/-sdk-configuration-response/index.md)&gt; |
| [encodePusherAuthRequest](encode-pusher-auth-request.md) | [androidJvm]<br>fun [encodePusherAuthRequest](encode-pusher-auth-request.md)(pusherAuthRequest: [PusherAuthRequest](../../com.delighted.sdk.domain.request/-pusher-auth-request/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [encodeSurveyAnswer](encode-survey-answer.md) | [androidJvm]<br>fun [encodeSurveyAnswer](encode-survey-answer.md)(surveyAnswerRequest: [SurveyAnswerRequest](../../com.delighted.sdk.domain.request/-survey-answer-request/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [encodeSurveyRequest](encode-survey-request.md) | [androidJvm]<br>fun [encodeSurveyRequest](encode-survey-request.md)(surveyRequest: [SurveyRequest](../../com.delighted.sdk.domain.request/-survey-request/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |

## Properties

| Name | Summary |
|---|---|
| [moshi](moshi.md) | [androidJvm]<br>val [moshi](moshi.md): Moshi |
