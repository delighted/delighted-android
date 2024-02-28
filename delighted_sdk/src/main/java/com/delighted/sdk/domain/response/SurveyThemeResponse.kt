package com.delighted.sdk.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SurveyThemeResponse(
    @Json(name = "primary_color") val primaryColor: String,
    @Json(name = "background_color") val backgroundColor: String,
    @Json(name = "primary_text_color") val primaryTextColor: String,
    @Json(name = "secondary_text_color") val secondaryTextColor: String,
    @Json(name = "button_style") val buttonStyle: ButtonStyle,
    @Json(name = "button_shape") val buttonShape: ButtonShape,
    @Json(name = "display") val display: String,
    @Json(name = "container_corner_radius") val containerCornerRadius: Int,
    @Json(name = "textarea") val textArea: TextAreaResponse,
    @Json(name = "scale") val scale: ScaleResponse,
    @Json(name = "stars") val stars: StarsResponse,
    @Json(name = "icon") val icon: IconResponse,
    @Json(name = "slider") val slider: SliderResponse,
    @Json(name = "ios") val ios: IosResponse,
    @Json(name = "primary_button") val primaryButton: BaseButton,
    @Json(name = "secondary_button") val secondaryButton: BaseButton,
    @Json(name = "button") val button: ButtonResponse,
    @Json(name = "close_button") val closeButton: CloseButtonResponse,
) {

    data class TextAreaResponse(
        @Json(name = "background_color") val backgroundColor: String,
        @Json(name = "border_color") val borderColor: String,
        @Json(name = "text_color") val textColor: String,
    )

    data class ScaleResponse(
        @Json(name = "active_background_color") val activeBackgroundColor: String,
        @Json(name = "active_text_color") val activeTextColor: String,
        @Json(name = "active_border_color") val activeBorderColor: String,
        @Json(name = "inactive_background_color") val inactiveBackgroundColor: String,
        @Json(name = "inactive_text_color") val inactiveTextColor: String,
        @Json(name = "inactive_border_color") val inactiveBorderColor: String
    )

    data class StarsResponse(
        @Json(name = "active_background_color") val activeBackgroundColor: String,
        @Json(name = "inactive_background_color") val inactiveBackgroundColor: String
    )

    data class SliderResponse(
        @Json(name = "knob_background_color") val knobBackgroundColor: String,
        @Json(name = "knob_text_color") val knobTextColor: String,
        @Json(name = "knob_border_color") val knobBorderColor: String,
        @Json(name = "track_active_color") val trackActiveColor: String,
        @Json(name = "track_inactive_color") val trackInactiveColor: String,
        @Json(name = "hover_background_color") val hoverBackgroundColor: String,
        @Json(name = "hover_border_color") val hoverBorderColor: String,
        @Json(name = "hover_text_color") val hoverTextColor: String
    )

    data class ButtonResponse(
        @Json(name = "active_background_color") val activeBackgroundColor: String,
        @Json(name = "active_border_color") val activeBorderColor: String,
        @Json(name = "active_text_color") val activeTextColor: String,
        @Json(name = "inactive_background_color") val inactiveBackgroundColor: String,
        @Json(name = "inactive_border_color") val inactiveBorderColor: String,
        @Json(name = "inactive_text_color") val inactiveTextColor: String
    )

    data class CloseButtonResponse(
        @Json(name = "normal_background_color") val normalBackgroundColor: String,
        @Json(name = "normal_border_color") val normalBorderColor: String,
        @Json(name = "normal_text_color") val normalTextColor: String,
        @Json(name = "highlighted_background_color") val highlightedBackgroundColor: String,
        @Json(name = "highlighted_border_color") val highlightedBorderColor: String,
        @Json(name = "highlighted_text_color") val highlightedTextColor: String
    )

    data class IconResponse(
        @Json(name = "active_background_color") val activeBackgroundColor: String,
        @Json(name = "inactive_background_color") val inactiveBackgroundColor: String
    )

    data class IosResponse(
        @Json(name = "status_bar_hidden") val statusBarHidden: Boolean,
        @Json(name = "status_bar_mode") val statusBarMode: String?,
        @Json(name = "keyboard_appearance") val keyboardResponse: String?
    )

    data class BaseButton(
        @Json(name = "background_color") val backgroundColor: String,
        @Json(name = "border_color") val borderColor: String,
        @Json(name = "text_color") val textColor: String,
    )
}
