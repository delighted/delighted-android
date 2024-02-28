//[delighted-android](../../../index.md)/[com.delighted.sdk](../index.md)/[SurveyInitResult](index.md)

# SurveyInitResult

[androidJvm]\
sealed class [SurveyInitResult](index.md)

Result states when for when the SDK tries to create a survey for the user

## Types

| Name | Summary |
|---|---|
| [ConfigFail](-config-fail/index.md) | [androidJvm]<br>data class [ConfigFail](-config-fail/index.md)(val throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?) : [SurveyInitResult](index.md) |
| [CreationAlreadyInProgress](-creation-already-in-progress/index.md) | [androidJvm]<br>object [CreationAlreadyInProgress](-creation-already-in-progress/index.md) : [SurveyInitResult](index.md) |
| [DeviceNotInPortrait](-device-not-in-portrait/index.md) | [androidJvm]<br>object [DeviceNotInPortrait](-device-not-in-portrait/index.md) : [SurveyInitResult](index.md) |
| [NotEligible](-not-eligible/index.md) | [androidJvm]<br>data class [NotEligible](-not-eligible/index.md)(val reason: [ClientEligibilityCheckUseCase.ClientEligibilityCheckResult.Fail](../../com.delighted.sdk.interactor/-client-eligibility-check-use-case/-client-eligibility-check-result/-fail/index.md)) : [SurveyInitResult](index.md) |
| [SurveyAlreadyInProgress](-survey-already-in-progress/index.md) | [androidJvm]<br>object [SurveyAlreadyInProgress](-survey-already-in-progress/index.md) : [SurveyInitResult](index.md) |
| [SurveyRequestFail](-survey-request-fail/index.md) | [androidJvm]<br>data class [SurveyRequestFail](-survey-request-fail/index.md)(val throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?) : [SurveyInitResult](index.md) |
| [SurveyReturned](-survey-returned/index.md) | [androidJvm]<br>object [SurveyReturned](-survey-returned/index.md) : [SurveyInitResult](index.md) |

## Inheritors

| Name |
|---|
| [ConfigFail](-config-fail/index.md) |
| [NotEligible](-not-eligible/index.md) |
| [SurveyRequestFail](-survey-request-fail/index.md) |
| [SurveyReturned](-survey-returned/index.md) |
| [CreationAlreadyInProgress](-creation-already-in-progress/index.md) |
| [SurveyAlreadyInProgress](-survey-already-in-progress/index.md) |
| [DeviceNotInPortrait](-device-not-in-portrait/index.md) |
