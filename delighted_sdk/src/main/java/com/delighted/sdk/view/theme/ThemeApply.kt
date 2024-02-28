package com.delighted.sdk.view.theme

import android.R.attr
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toColorInt
import androidx.core.view.children
import com.delighted.sdk.R
import com.delighted.sdk.core.dipFtoPxF
import com.delighted.sdk.core.dipToPx
import com.delighted.sdk.databinding.ViewAqBottomBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.domain.response.ButtonShape.CIRCLE
import com.delighted.sdk.domain.response.ButtonShape.ROUND_RECT
import com.delighted.sdk.domain.response.ButtonShape.SQUARE
import com.delighted.sdk.domain.response.ButtonStyle.OUTLINE
import com.delighted.sdk.domain.response.ButtonStyle.SOLID
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.RangeSlider

class ThemeApply private constructor(private val theme: SurveyTheme) {

    fun applyAllTextViews(parentLayout: ViewGroup) {
        parentLayout.children.filterIsInstance<TextView>()
            .forEach { it.setTextColor(theme.primaryTextColor.toColorInt()) }
    }

    fun applyLabels(parentLayout: ViewGroup) {
        parentLayout.children.filterIsInstance<TextView>()
            .forEach { it.setTextColor(theme.secondaryTextColor.toColorInt()) }
    }

    fun applyBackground(view: View) {
        view.background = ColorDrawable(theme.backgroundColor.toColorInt())
    }

    fun applyBackgroundTint(view: View) {
        view.backgroundTintList = theme.backgroundColor.toColorStateList()
    }

    fun applyCloseButton(closeButton: View) {
        val states = arrayOf(
            intArrayOf(attr.state_pressed),
            intArrayOf(),
        )
        val colors = intArrayOf(
            theme.closeButton.highlightedBackgroundColor.toColorInt(),
            // purposely ignoring normal bg color from spec, using global bg coolor intead
            theme.backgroundColor.toColorInt()
        )
        val colorStateList = ColorStateList(states, colors)
        closeButton.backgroundTintList = colorStateList
    }

    fun applyBaseIconStateful(
        views: List<View>
    ) {
        val states = arrayOf(
            intArrayOf(attr.state_pressed),
            intArrayOf(attr.state_checked),
            intArrayOf(),
        )
        val colors = intArrayOf(
            theme.icon.activeBackgroundColor.toColorInt(),
            theme.icon.activeBackgroundColor.toColorInt(),
            theme.icon.inactiveBackgroundColor.toColorInt(),
        )
        val colorStateList = ColorStateList(states, colors)
        views.forEach { it.backgroundTintList = colorStateList }
    }

    fun applyStarStateful(stars: List<View>, resources: Resources) {
        val states = arrayOf(
            intArrayOf(attr.state_pressed),
            intArrayOf(attr.state_checked),
            intArrayOf(),
        )
        val backgroundColors = when (theme.buttonStyle) {
            OUTLINE -> intArrayOf(
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.activeBackgroundColor.toColorInt(),
                // using inactiveBackgroundColor will not work because the server sends the same value
                // for active and inactive background color if button is OUTLINE and survey is Stars
                Color.TRANSPARENT
            )
            SOLID -> intArrayOf(
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.inactiveBackgroundColor.toColorInt(),
            )
        }
        val foregroundColors = when (theme.buttonStyle) {
            OUTLINE -> intArrayOf(
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.inactiveBackgroundColor.toColorInt(),
            )
            SOLID -> intArrayOf(
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.activeBackgroundColor.toColorInt(),
                theme.stars.inactiveBackgroundColor.toColorInt(),
            )
        }
        stars.forEach {
            it.backgroundTintList = ColorStateList(states, backgroundColors)
            it.foregroundTintList = ColorStateList(states, foregroundColors)
            applyStarButtonShape(it as Button, resources)
        }
    }

    private fun applyStarButtonShape(button: Button, resources: Resources) {
        button.background = ResourcesCompat.getDrawable(
            resources, R.drawable.ic_star, null
        )
        button.foreground = ResourcesCompat.getDrawable(
            resources, R.drawable.ic_star_outline, null
        )
    }

    fun applyPrimaryButton(button: MaterialButton, context: Context) {
        val primaryButtonTheme = theme.primaryButton
        button.backgroundTintList =
            primaryButtonTheme.backgroundColor.toColorStateList()
        button.setTextColor(primaryButtonTheme.textColor.toColorInt())
        button.cornerRadius = when (theme.buttonShape) {
            SQUARE -> 0
            ROUND_RECT -> context.dipToPx(8f)
            CIRCLE -> context.dipToPx(22f)
        }
    }

    private fun applySecondaryButton(button: MaterialButton, context: Context) {
        val secondaryButtonTheme = theme.secondaryButton
        button.background = GradientDrawable().apply {
            cornerRadius = when (theme.buttonShape) {
                SQUARE -> 0f
                ROUND_RECT -> context.dipFtoPxF(8f)
                CIRCLE -> context.dipFtoPxF(22f)
            }
            setStroke(context.dipToPx(1f), Color.WHITE)
            setColor(Color.TRANSPARENT)
        }
        button.backgroundTintList =
            secondaryButtonTheme.borderColor.toColorStateList() // secondary button is always outlined
        button.setTextColor(secondaryButtonTheme.textColor.toColorInt())
    }

    fun applyScaleStateful(buttons: List<Button>, resources: Resources) {
        val states = arrayOf(
            intArrayOf(attr.state_checked),
            intArrayOf(),
        )
        val backgroundColors = when (theme.buttonStyle) {
            OUTLINE -> intArrayOf(
                theme.scale.activeBorderColor.toColorInt(),
                theme.scale.inactiveBorderColor.toColorInt(),
            )
            SOLID -> intArrayOf(
                theme.scale.activeBackgroundColor.toColorInt(),
                theme.scale.inactiveBackgroundColor.toColorInt(),
            )
        }
        val textColors = intArrayOf(
            theme.scale.activeTextColor.toColorInt(),
            theme.scale.inactiveTextColor.toColorInt(),
        )
        val backgroundColorStateList = ColorStateList(states, backgroundColors)
        val textColorStateList = ColorStateList(states, textColors)
        buttons.forEach {
            applyScaleButtonShape(it, resources)
            it.backgroundTintList = backgroundColorStateList
            it.setTextColor(textColorStateList)
        }
    }

    private fun applyScaleButtonShape(button: Button, resources: Resources) {
        val drawableId = when (theme.buttonStyle) {
            OUTLINE -> when (theme.buttonShape) {
                SQUARE -> R.drawable.shape_square_outline
                ROUND_RECT -> R.drawable.shape_rounded_rect_8dp_outline
                CIRCLE -> R.drawable.shape_circle_outline
            }
            SOLID -> when (theme.buttonShape) {
                SQUARE -> R.drawable.shape_square
                ROUND_RECT -> R.drawable.shape_rounded_rect_8dp
                CIRCLE -> R.drawable.shape_circle
            }
        }
        button.background = ResourcesCompat.getDrawable(resources, drawableId, null)
    }

    fun applySlider(slider: RangeSlider) {
        slider.thumbTintList = ColorStateList.valueOf(theme.slider.knobBackgroundColor.toColorInt())
        slider.thumbStrokeColor = ColorStateList.valueOf(theme.slider.knobBorderColor.toColorInt())
        slider.trackActiveTintList =
            ColorStateList.valueOf(theme.slider.trackActiveColor.toColorInt())
        slider.trackInactiveTintList =
            ColorStateList.valueOf(theme.slider.trackInactiveColor.toColorInt())
        // swap the inactive/active colors for ticks so they show against the track
        slider.tickActiveTintList =
            ColorStateList.valueOf(theme.slider.trackInactiveColor.toColorInt())
        slider.tickInactiveTintList =
            ColorStateList.valueOf(theme.slider.trackActiveColor.toColorInt())
    }

    fun applyAqSelectableButton(button: Button, resources: Resources) {
        val states = arrayOf(
            intArrayOf(attr.state_checked),
            intArrayOf(),
        )
        val bgColors = intArrayOf(
            theme.button.activeBackgroundColor.toColorInt(),
            theme.button.inactiveBorderColor.toColorInt(), // background color becomes the border
        )
        val textColors = intArrayOf(
            theme.button.activeTextColor.toColorInt(),
            theme.button.inactiveTextColor.toColorInt()
        )

        val backgroundDrawables = StateListDrawable()
        val inactiveDrawable = when (theme.buttonShape) {
            SQUARE -> R.drawable.shape_square_outline
            ROUND_RECT -> R.drawable.shape_rounded_rect_8dp_outline
            CIRCLE -> R.drawable.shape_pill_outline
        }
        val activeDrawable = when (theme.buttonShape) {
            SQUARE -> R.drawable.shape_square
            ROUND_RECT -> R.drawable.shape_rounded_rect_8dp
            CIRCLE -> R.drawable.shape_pill
        }
        backgroundDrawables.addState(
            intArrayOf(attr.state_checked),
            ResourcesCompat.getDrawable(resources, activeDrawable, null)
        )
        backgroundDrawables.addState(
            intArrayOf(),
            ResourcesCompat.getDrawable(resources, inactiveDrawable, null)
        )

        button.background = backgroundDrawables
        button.backgroundTintList = ColorStateList(states, bgColors)
        button.setTextColor(ColorStateList(states, textColors))
    }

    fun applyAqBottom(aqBottom: ViewAqBottomBinding, context: Context) {
        applyPrimaryButton(aqBottom.primaryButton, context)
        applySecondaryButton(aqBottom.secondaryButton, context)
        applyPoweredBy(aqBottom.poweredByText)
    }

    fun applyPoweredBy(view: TextView) {
        view.setTextColor(theme.secondaryTextColor.toColorInt())
    }

    fun applyTextInput(inputArea: TextView, border: View) {
        border.backgroundTintList =
            theme.textArea.borderColor.toColorStateList()
        inputArea.backgroundTintList =
            theme.textArea.backgroundColor.toColorStateList()
        inputArea.setTextColor(theme.textArea.textColor.toColorInt())
    }

    fun applyInstructionText(view: TextView) {
        view.setTextColor(theme.secondaryTextColor.toColorInt())
    }

    fun applySecondaryThankYouText(view: TextView) {
        view.setTextColor(theme.secondaryTextColor.toColorInt())
    }

    companion object {
        fun SurveyTheme.applyDltTheme(action: ThemeApply.() -> Unit) {
            ThemeApply(this).action()
        }

        fun SurveyTheme.themeApply() = ThemeApply(this)
    }

    fun String.toColorStateList(): ColorStateList =
        ColorStateList.valueOf(this.toColorInt())
}
