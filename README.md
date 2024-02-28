# Delighted CX Android SDK

![Android](https://img.shields.io/badge/platform-android-green.svg)

The [Delighted](https://delighted.com) Android SDK makes it quick and easy to gather customer feedback directly in your Android app.

## Requirements

### Screen orientation
The SDK provides UI that is locked to portrait. Device rotation will not change the layout of the survey and the survey will not start successfully while a device is held in any other orientation.

### Dependencies
Development and runtime dependencies used by the SDK are listed on the bottom of this page.

## Installation

### Maven

The easiest way to install Delighted SDK on your app is to add a dependency to the SDK in your `build.gradle`.

Delighted SDK is available through a Maven repository on S3, and you will need to instruct Gradle to use this repo.

```kotlin
// file: build.gradle.kts
repositories {
	maven {
		url = URI("https://s3.amazonaws.com/delighted-android-sdk-public")
	}
}
```

Provide the Maven coordinates for the SDK with the latest version. A list of released versions are available on our [Github releases](https://github.com/delighted/delighted-android/releases) page.

```kotlin
// file: build.gradle.kts
dependencies {
	// other dependencies

	// Delighted SDK
	implementation("com.delighted.sdk", "android", "1.0.+")
}
```

### Direct dependency on the AAR file

If you do not want to use Maven, you can download the AAR file from our [Github releases](https://github.com/delighted/delighted-android/releases) page and import it into Android Studio.

## Getting Started

SurveySdk is the entry point to setup, create and show surveys. It is strongly recommended that host app maintains a single source that instantiates the SurveySdk and maintains it with an appropriate Activity lifecycle.

One of the easiest ways to instantiate a SurveySdk is to use a dependency injection framework like [Hilt Dagger](https://developer.android.com/training/dependency-injection/hilt-android).

A recommended approach to manage the lifecycle of the SurveySdk instance is using a [ViewModel architecture](https://developer.android.com/topic/libraries/architecture/viewmodel).

Please note that the SDk does not enforce this particular dependency injection and lifecycle management frameworks - host apps are free to use any they choose. The sample code for these frameworks are provided since the Sample Delighted App uses them and also because they are popular frameworks.

```kotlin
// file: build.gradle.kts
implementation("com.google.dagger", "hilt-android", "2.41")
```

```kotlin
// file: MainViewModel.kt
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.delighted.sdk.SurveySdk
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	application: Application,
	val surveySdk: SurveySdk
) : AndroidViewModel(application)
```

```kotlin
// file: MainActivity.kt
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.delighted.sdk.SdkInitParams

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private val viewModel by viewModels<MainViewModel>()

	private suspend fun surveyButtonClicked() {
        val surveyCreationResult = viewModel.surveySdk.createAndLaunchSurvey(
            context = this@MainActivity,
            options = null,
            sdkInitParams = SdkInitParams(
				delightedId = "mobile-sdk-xxxxxxxxx"
            )
        )
    }
}
```

For a comprehensive example on how to use the SurveySdk in your application, see the [Delighted Sample App](app/src/main/java/com/delighted/sampleapp/MainActivity.kt)

### Delighted ID
When your code calls the survey function it needs to pass in a delightedID. You can find your Delighted ID at https://delighted.com/integrations/mobile_sdk. The Delighted ID is considered public and can be treated like any other configuration setting in your app.

If you have multiple projects, each project will have its own unique ID. This allows you to include multiple surveys in your app.

### Examples
Note: `SdkInitParams` is used to configure additional values while setting up a survey.

The only required parameter is the Delighted ID.
```kotlin
val sdkInitParams = SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx"
)
surveySdk.createAndLaunchSurvey(
	context = this@MainActivity,
	options = null,
	sdkInitParams = sdkInitParams
)
```

A survey can also be linked to an individual by passing in a `Person`.
```kotlin
val person = Person(
	name = "Lauren Ipsum",
	email = "laurenipsum@example.com",
	phoneNumber = "+14155551212"
)
val sdkInitParams = SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	person = person
)
```

Like other Delighted platforms, you can associate properties with a survey to provide context and segment responses.

```kotlin
val properties = mapOf(
    "customerId" to "123",
    "location" to "USA"
)
SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	properties = properties
)
```

Delighted special properties can be used to further customize the survey experience. For example, to change the survey to appear in German you might use something like this:

```kotlin
val properties = mapOf(
    "locale" to "de"
)
SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	properties = properties
)
```

You may need to override the eligibility criteria during development and testing. The `testMode` flag will bypass all checks and force the survey to appear.

```kotlin
SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	testMode = true
)
```

The `initialDelay` can be passed to specify the number of seconds to wait before showing a person their first survey. This is useful in cases where you want a user to have been using your app at least some amount of time before they would be eligible to be surveyed.

```kotlin
SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	initialDelay = 86400
)
```

When the SDK is initialized for the very first time a timestamp is stored on the device. The initialDelay is based on that value. If you would like to use a different reference point for the initial delay, you can set createdAt.

The `recurringPeriod` (in seconds) applies when you want to control how frequently to survey a person.

```kotlin
SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	initialDelay = 86400,
	createdAt = yourUserModel.createdAt,
	recurringPeriod = 1036800
)
```

### Handling status and errors
The `SurveySdk#createAndLaunchSurvey` method returns a result object that can be inspected to determine the status of survey creation.

```kotlin
val result = surveySdk.createAndLaunchSurvey(
	context = this@MainActivity,
	sdkInitParmas = sdkInitParms
)
when (result) {
	is SurveyReturned -> {
		// Survey was created and shown
	}
	is NotEligible -> {
		// Not eligible to show survey at this time
	}
	is ConfigFail -> {
		// Failed to get config
	}
	is SurveyRequestFail -> {
		// Could not create a survey on the server
	}
	is CreationAlreadyInProgress -> {
		// Survey is already being created
	}
	is SurveyAlreadyInProgress -> {
		// User is currently taking a survey
	}
	is DeviceNotInPortrait -> {
		// Can not launch a survey while in landscape mode
	}
}
```

### Presentation
The Delighted SDK offers a number of settings to control the survey’s appearance.

* The background, border, and text color of every component can be changed.
* You can choose whether to prompt the user with a centered modal or a card popping up from the bottom of the screen.
* Buttons shapes can be circles, squares, or squares with rounded corners.
* Buttons can also be filled or outlined.
* You can control whether the Thank You page should remain open until a person explicitly closes it or to automatically close it after some period (the default is to close it after two seconds).

The most common settings can be updated by browsing to the Mobile SDK’s [Customize appearance](https://delighted.com/platforms/preview_mobile_sdk) page.
Please reach out to Delighted’s [Customer Concierge](mailto:hello@delighted.com) team if you would like to change any of the other settings.

Alternatively, you can control the appearance of the survey in your own code. The values you provide take precedence over your Delighted project’s configuration. If you opt to describe the settings through code you will need to provide values for all theme settings. We encourage you to reach out to Delighted’s [Customer Concierge](mailto:hello@delighted.com) team to help you evaluate the trade-offs and make the best choice for your app.

```kotlin
val localTheme = SurveyTheme(
	primaryColor = LocalTheme.primaryColor,
	backgroundColor = LocalTheme.backgroundColor,
	primaryTextColor = LocalTheme.primaryTextColor,
	secondaryTextColor = LocalTheme.secondaryTextColor,
	buttonStyle = ButtonStyle.OUTLINE,
	buttonShape = ButtonShape.CIRCLE,
	display = "card",
	containerCornerRadius = 20,
	textArea = SurveyTheme.TextAreaResponse(
		backgroundColor = LocalTheme.textAreaBackgroundColor,
		borderColor = LocalTheme.textAreaBorderColor,
		textColor = LocalTheme.textAreaTextColor
	),
	scale = SurveyTheme.Scale(
		activeBackgroundColor = LocalTheme.scaleActiveBackgroundColor,
		activeTextColor = LocalTheme.scaleActiveTextColor,
		activeBorderColor = LocalTheme.scaleActiveBorderColor,
		inactiveBackgroundColor = LocalTheme.scaleInactiveBackgroundColor,
		inactiveTextColor = LocalTheme.scaleInactiveTextColor,
		inactiveBorderColor = LocalTheme.scaleInactiveBorderColor
	),
	stars = SurveyTheme.Stars(
		activeBackgroundColor = LocalTheme.starsActiveBackgroundColor,
		inactiveBackgroundColor = LocalTheme.starsInactiveBackgroundColor
	),
	icon = SurveyTheme.Icon(
		activeBackgroundColor = LocalTheme.iconActiveBackgroundColor,
		inactiveBackgroundColor = LocalTheme.iconInactiveBackgroundColor
	),
	slider = SurveyTheme.Slider(
		knobBackgroundColor = LocalTheme.sliderKnobBackgroundColor,
		knobTextColor = LocalTheme.sliderKnobTextColor,
		knobBorderColor = LocalTheme.sliderKnobBorderColor,
		trackActiveColor = LocalTheme.sliderTrackActiveColor,
		trackInactiveColor = LocalTheme.sliderTrackInactiveColor,
		hoverBackgroundColor = LocalTheme.sliderHoverBackgroundColor,
		hoverBorderColor = LocalTheme.sliderHoverBorderColor,
		hoverTextColor = LocalTheme.sliderHoverTextColor
	),
	primaryButton = SurveyTheme.BaseButton(
		backgroundColor = LocalTheme.primaryButtonBackgroundColor,
		borderColor = LocalTheme.primaryButtonBorderColor,
		textColor = LocalTheme.primaryButtonTextColor,
	),
	secondaryButton = SurveyTheme.BaseButton(
		backgroundColor = LocalTheme.secondaryButtonBackgroundColor,
		borderColor = LocalTheme.secondaryButtonBorderColor,
		textColor = LocalTheme.secondaryButtonTextColor,
	),
	button = SurveyTheme.Button(
		activeBackgroundColor = LocalTheme.buttonActiveBackgroundColor,
		activeTextColor = LocalTheme.buttonActiveTextColor,
		activeBorderColor = LocalTheme.buttonActiveBorderColor,
		inactiveBackgroundColor = LocalTheme.buttonInactiveBackgroundColor,
		inactiveTextColor = LocalTheme.buttonInactiveTextColor,
		inactiveBorderColor = LocalTheme.buttonInactiveBorderColor
	),
	closeButton = SurveyTheme.CloseButton(
		normalBackgroundColor = LocalTheme.closeButtonNormalBackgroundColor,
		normalBorderColor = LocalTheme.closeButtonNormalBorderColor,
		normalTextColor = LocalTheme.closeButtonNormalTextColor,
		highlightedBackgroundColor = LocalTheme.closeButtonHighlightedBackgroundColor,
		highlightedBorderColor = LocalTheme.closeButtonHighlightedBorderColor,
		highlightedTextColor = LocalTheme.closeButtonHighlightedTextColor
	)
)

SdkInitParams(
	delightedId = "mobile-sdk-xxxxxxxxx",
	themeOverride = localTheme
)
```

## Dependencies

## Development and Runtime Dependencies

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/): Android architecture components are part of [Android Jetpack](https://developer.android.com/jetpack). They are a collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence. We included:
	- [Room](https://developer.android.com/topic/libraries/architecture/room)
	- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
	- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Android KTX](https://developer.android.com/kotlin/ktx): A set of Kotlin extensions for Android app development. The goal of Android KTX is to make Android development with Kotlin more concise, pleasant, and idiomatic by leveraging the features of the language such as extension functions/properties, lambdas, named parameters, and parameter defaults.
	- [List of available extensions](https://developer.android.com/kotlin/ktx/extensions-list)
* [Kotlin](https://kotlinlang.org/docs/reference/using-gradle.html): Kotlin is a modern statically typed programming language that will boost your productivity and increase your developer happiness.
* [KotlinX Coroutines](https://kotlinlang.org/docs/coroutines-guide.html): Coroutines are used to write asynchronous, non-blocking code.
* [Dagger](https://github.com/google/dagger): Dagger is a fully static, compile-time dependency injection framework for both Java and Android.
* [Hilt](https://dagger.dev/hilt/): Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
* [Material Components Android](https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md): Allows developers to implement [Material Design](https://material.io/design) in Android applications.
* [Moshi](https://github.com/square/moshi): Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects.
* [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Websocket](https://central.sonatype.com/artifact/org.java-websocket/Java-WebSocket) is used to transmit state while user is responding to a survey.

In addition to these dependencies the project utilizes [core library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) in order to take advantage of Java 8 APIs such as `java.util.Optional`, `java.util.stream`, and `java.time`.

### Test Dependencies

* [AndroidX Test](https://developer.android.com/training/testing/set-up-project): Dependencies needed for running Android tests.
* [JUnit](https://github.com/junit-team/junit4): JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
* [MockK](https://github.com/mockk/mockk)
* Kotlin-test

## Billing

Like Delighted’s web platform, the SDK bases usage on the number of responses. When your account exceeds its plan limits the survey won’t appear in your Android app until the next billing period or you switch to a plan with a higher limit.

## Support

Please contact the [Delighted Concierge](mailto:hello@delighted.com) team with any questions or to report an issue.

## Example App
The [Example App](app) included in the SDK shows examples of each type of survey. This is a fully working app using Delighted’s Demo for the source of questions.

To update the sample app to show a survey from your Delighted Project, update the `delightedId` in [MainActivity#74](app/src/main/java/com/delighted/sampleapp/MainActivity.kt#74) and build the app.

### Setup instructions
1. Clone/fork this repository
2. Build the app and deploy to your android emulator or device.
```bash
./gradlew app:build
```
