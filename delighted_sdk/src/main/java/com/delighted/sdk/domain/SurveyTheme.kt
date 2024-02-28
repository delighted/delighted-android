package com.delighted.sdk.domain

import com.delighted.sdk.domain.response.ButtonShape
import com.delighted.sdk.domain.response.ButtonStyle

data class SurveyTheme(
    val primaryColor: String,
    val backgroundColor: String,
    val primaryTextColor: String,
    val secondaryTextColor: String,
    val buttonStyle: ButtonStyle,
    val buttonShape: ButtonShape,
    val display: String,
    val containerCornerRadius: Int,
    val textArea: TextAreaResponse,
    val scale: Scale,
    val stars: Stars,
    val icon: Icon,
    val slider: Slider,
    val ios: Ios,
    val primaryButton: BaseButton,
    val secondaryButton: BaseButton,
    val button: Button,
    val closeButton: CloseButton,
) {

    data class TextAreaResponse(
        val backgroundColor: String,
        val borderColor: String,
        val textColor: String,
    )

    data class Scale(
        val activeBackgroundColor: String,
        val activeTextColor: String,
        val activeBorderColor: String,
        val inactiveBackgroundColor: String,
        val inactiveTextColor: String,
        val inactiveBorderColor: String
    )

    data class Stars(
        val activeBackgroundColor: String,
        val inactiveBackgroundColor: String
    )

    data class Slider(
        val knobBackgroundColor: String,
        val knobTextColor: String,
        val knobBorderColor: String,
        val trackActiveColor: String,
        val trackInactiveColor: String,
        val hoverBackgroundColor: String,
        val hoverBorderColor: String,
        val hoverTextColor: String
    )

    data class Button(
        val activeBackgroundColor: String,
        val activeBorderColor: String,
        val activeTextColor: String,
        val inactiveBackgroundColor: String,
        val inactiveBorderColor: String,
        val inactiveTextColor: String
    )

    data class CloseButton(
        val normalBackgroundColor: String,
        val normalBorderColor: String,
        val normalTextColor: String,
        val highlightedBackgroundColor: String,
        val highlightedBorderColor: String,
        val highlightedTextColor: String
    )

    data class Icon(
        val activeBackgroundColor: String,
        val inactiveBackgroundColor: String
    )

    data class Ios(
        val statusBarHidden: Boolean,
        val statusBarMode: String?,
        val keyboardResponse: String?
    )

    data class BaseButton(
        val backgroundColor: String,
        val borderColor: String,
        val textColor: String,
    )
}
