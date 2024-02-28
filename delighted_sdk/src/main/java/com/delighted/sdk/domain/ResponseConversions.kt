package com.delighted.sdk.domain

import com.delighted.sdk.domain.response.SdkConfigurationResponse
import com.delighted.sdk.domain.response.SurveyTemplateResponse
import com.delighted.sdk.domain.response.SurveyThemeResponse
import com.delighted.sdk.domain.response.SurveyTypeResponse
import com.delighted.sdk.domain.response.ThankYouResponse

internal fun fromResponse(sdkConfigResponse: SdkConfigurationResponse) =
    SdkConfiguration(
        enabled = sdkConfigResponse.enabled,
        androidEnabled = sdkConfigResponse.androidEnabled,
        sampleFactor = sdkConfigResponse.sampleFactor,
        recurringSurveyPeriod = sdkConfigResponse.recurringSurveyPeriod,
        minSurveyInterval = sdkConfigResponse.minSurveyInterval,
        initialSurveyDelay = sdkConfigResponse.initialSurveyDelay,
        planLimitExhausted = sdkConfigResponse.planLimitExhausted,
        forceDisplay = sdkConfigResponse.forceDisplay,
    )

internal fun fromResponse(thankYouResponse: ThankYouResponse): ThankYou {
    return ThankYou(
        text = thankYouResponse.text,
        autoCloseDelay = thankYouResponse.autoCloseDelay,
        thankYouMessageGroups = thankYouResponse.groups?.map { entry ->
            ThankYouMessageGroup(
                name = entry.key,
                linkText = entry.value["link_text"],
                linkUrl = entry.value["link_url"],
                messageText = entry.value["message_text"],
            )
        }.orEmpty()
    )
}

internal fun SurveyTemplateResponse.fromResponse(): SurveyTemplate {
    return SurveyTemplate(
        questionText = questionText,
        commentPrompts = commentPrompts?.mapKeys { it.key.toInt() }
            ?.toSortedMap(),
        scoreText = scoreText.mapKeys { it.key.toInt() }
            .toSortedMap(),
        additionalQuestions = additionalQuestions
    )
}

internal fun fromResponse(surveyThemeResponse: SurveyThemeResponse): SurveyTheme {
    return SurveyTheme(
        primaryColor = surveyThemeResponse.primaryColor,
        backgroundColor = surveyThemeResponse.backgroundColor,
        primaryTextColor = surveyThemeResponse.primaryTextColor,
        secondaryTextColor = surveyThemeResponse.secondaryTextColor,
        buttonStyle = surveyThemeResponse.buttonStyle,
        buttonShape = surveyThemeResponse.buttonShape,
        display = surveyThemeResponse.display,
        containerCornerRadius = surveyThemeResponse.containerCornerRadius,
        textArea = SurveyTheme.TextAreaResponse(
            backgroundColor = surveyThemeResponse.textArea.backgroundColor,
            borderColor = surveyThemeResponse.textArea.borderColor,
            textColor = surveyThemeResponse.textArea.textColor,
        ),
        scale = SurveyTheme.Scale(
            activeBackgroundColor = surveyThemeResponse.scale.activeBackgroundColor,
            activeTextColor = surveyThemeResponse.scale.activeTextColor,
            activeBorderColor = surveyThemeResponse.scale.activeBorderColor,
            inactiveBackgroundColor = surveyThemeResponse.scale.inactiveBackgroundColor,
            inactiveTextColor = surveyThemeResponse.scale.inactiveTextColor,
            inactiveBorderColor = surveyThemeResponse.scale.inactiveBorderColor,
        ),
        stars = SurveyTheme.Stars(
            activeBackgroundColor = surveyThemeResponse.stars.activeBackgroundColor,
            inactiveBackgroundColor = surveyThemeResponse.stars.inactiveBackgroundColor
        ),
        icon = SurveyTheme.Icon(
            activeBackgroundColor = surveyThemeResponse.icon.activeBackgroundColor,
            inactiveBackgroundColor = surveyThemeResponse.icon.inactiveBackgroundColor,
        ),
        slider = SurveyTheme.Slider(
            knobBackgroundColor = surveyThemeResponse.slider.knobBackgroundColor,
            knobTextColor = surveyThemeResponse.slider.knobTextColor,
            knobBorderColor = surveyThemeResponse.slider.knobBorderColor,
            trackActiveColor = surveyThemeResponse.slider.trackActiveColor,
            trackInactiveColor = surveyThemeResponse.slider.trackInactiveColor,
            hoverBackgroundColor = surveyThemeResponse.slider.hoverBackgroundColor,
            hoverBorderColor = surveyThemeResponse.slider.hoverBorderColor,
            hoverTextColor = surveyThemeResponse.slider.hoverTextColor
        ),
        ios = SurveyTheme.Ios(
            statusBarHidden = surveyThemeResponse.ios.statusBarHidden,
            statusBarMode = surveyThemeResponse.ios.statusBarMode,
            keyboardResponse = surveyThemeResponse.ios.keyboardResponse,
        ),
        primaryButton = SurveyTheme.BaseButton(
            backgroundColor = surveyThemeResponse.primaryButton.backgroundColor,
            borderColor = surveyThemeResponse.primaryButton.borderColor,
            textColor = surveyThemeResponse.primaryButton.textColor,
        ),
        secondaryButton = SurveyTheme.BaseButton(
            backgroundColor = surveyThemeResponse.secondaryButton.backgroundColor,
            borderColor = surveyThemeResponse.secondaryButton.borderColor,
            textColor = surveyThemeResponse.secondaryButton.textColor,
        ),
        button = SurveyTheme.Button(
            activeBackgroundColor = surveyThemeResponse.button.activeBackgroundColor,
            activeBorderColor = surveyThemeResponse.button.activeBorderColor,
            activeTextColor = surveyThemeResponse.button.activeTextColor,
            inactiveBackgroundColor = surveyThemeResponse.button.inactiveBackgroundColor,
            inactiveBorderColor = surveyThemeResponse.button.inactiveBorderColor,
            inactiveTextColor = surveyThemeResponse.button.inactiveTextColor,
        ),
        closeButton = SurveyTheme.CloseButton(
            normalBackgroundColor = surveyThemeResponse.closeButton.normalBackgroundColor,
            normalBorderColor = surveyThemeResponse.closeButton.normalBorderColor,
            normalTextColor = surveyThemeResponse.closeButton.normalTextColor,
            highlightedBackgroundColor = surveyThemeResponse.closeButton.highlightedBackgroundColor,
            highlightedBorderColor = surveyThemeResponse.closeButton.highlightedBorderColor,
            highlightedTextColor = surveyThemeResponse.closeButton.highlightedTextColor,
        )
    )
}

fun fromResponse(surveyTypeResponse: SurveyTypeResponse): SurveyType {
    return SurveyType(
        id = surveyTypeResponse.id,
        surveyUserGroups = surveyTypeResponse.groups.map { entry ->
            SurveyUserGroup(
                name = entry.key,
                scoreMax = entry.value["score_max"]?.toInt() ?: -1,
                scoreMin = entry.value["score_min"]?.toInt() ?: -1,
            )
        }
    )
}
