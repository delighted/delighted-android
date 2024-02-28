package com.delighted.sdk.view.question

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import com.delighted.sdk.R
import com.delighted.sdk.R.layout
import com.delighted.sdk.core.dipFtoPxF
import com.delighted.sdk.databinding.PopupSliderPipBinding
import com.delighted.sdk.databinding.ViewSliderBinding
import com.delighted.sdk.domain.SurveyTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.RangeSlider.OnSliderTouchListener
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.roundToInt

class SliderQuestion(
    private val choiceIndexes: List<Int>,
    theme: SurveyTheme,
) : QuestionWidget<ViewSliderBinding>(theme), ScaleQuestion {

    @LayoutRes
    override val layoutId = layout.view_slider

    private lateinit var viewBinding: ViewSliderBinding
    private lateinit var popupPipBinding: PopupSliderPipBinding

    private lateinit var popupPip: PopupWindow
    private var labeledPipThumbDrawable: LabeledPipDrawable? = null

    private var pipSize: Int = 0
    private var pipPadding: Int = 0
    private var tickCount: Int = 0
    private lateinit var ticksCoordinates: FloatArray

    override fun setupWidget(
        parent: ViewGroup,
        setAnswerValue: (Int) -> Unit
    ): ViewSliderBinding {
        pipSize = parent.resources.getDimensionPixelSize(R.dimen.pip_size)
        pipPadding = parent.resources.getDimensionPixelSize(R.dimen.pip_padding)

        popupPip = PopupWindow(
            LayoutInflater.from(parent.context).inflate(layout.popup_slider_pip, null, false),
            pipSize + pipPadding * 2,
            pipSize + pipPadding * 2,
            false
        )
        popupPipBinding = PopupSliderPipBinding.bind(popupPip.contentView)

        viewBinding = ViewSliderBinding.bind(inflateWidget(parent = parent)).apply {
            setupSlider(rangeSlider, setAnswerValue)
        }

        return viewBinding
    }

    private fun setupSlider(rangeSlider: RangeSlider, setAnswerValue: (Int) -> Unit) {
        with(rangeSlider) {
            valueFrom = choiceIndexes.first().toFloat()
            valueTo = choiceIndexes.last().toFloat()
            stepSize = 1f

            val middleIndex = floor(choiceIndexes.size / 2f).toInt()
            val startingValue = choiceIndexes[middleIndex]
            setValues(startingValue.toFloat())

            tickCount = ((valueTo - valueFrom) / stepSize + 1).toInt()
            ticksCoordinates = FloatArray(tickCount)

            theme.applyDltTheme {
                applySlider(this@with)
            }

            val startPipLabel = startingValue.toString()
            val pipTextSize = context.dipFtoPxF(14f)
            val pipBackgroundColor = theme.slider.knobBackgroundColor.toColorInt()

            // initialize with no text
            labeledPipThumbDrawable = LabeledPipDrawable(
                text = "",
                knobColor = pipBackgroundColor,
                knobRadius = context.dipFtoPxF(20f),
                textColor = theme.slider.knobTextColor.toColorInt(),
                textSize = pipTextSize
            ).also {
                setCustomThumbDrawable(it)
            }

            val labeledFloatingPipDrawable = LabeledPipDrawable(
                text = startPipLabel,
                knobColor = theme.backgroundColor.toColorInt(),
                knobRadius = pipSize / 2f,
                textColor = pipBackgroundColor,
                textSize = pipTextSize,
                borderColor = pipBackgroundColor,
                borderStrokeWidth = context.dipFtoPxF(2f)
            )
            popupPipBinding.thumb.background = labeledFloatingPipDrawable

            addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
                if (trackWidth == 0) return@addOnLayoutChangeListener

                // Limit the tickCount if they will be too dense. Matches logic in BaseSlider
                tickCount = min(tickCount, trackWidth / (trackHeight * 2) + 1)
                if (ticksCoordinates.size != tickCount) ticksCoordinates = FloatArray(tickCount)

                val interval = trackWidth / (tickCount - 1).toFloat()
                for (i in 0 until tickCount) {
                    ticksCoordinates[i] = trackSidePadding + (i * interval)
                }

                // Reverse ticks if slider is in right to left
                if (isRtl(rangeSlider)) {
                    ticksCoordinates.reverse()
                }
            }

            addOnChangeListener { _, value, _ ->
                labeledFloatingPipDrawable.text = value.toInt().toString()
                labeledFloatingPipDrawable.invalidateSelf()
                placePopupPip(rangeSlider, value)
            }

            addOnSliderTouchListener(object :
                    OnSliderTouchListener {
                    override fun onStartTrackingTouch(slider: RangeSlider) {
                        placePopupPip(slider, values.getOrNull(0) ?: 0f, true)
                        labeledPipThumbDrawable?.text = ""
                    }

                    override fun onStopTrackingTouch(slider: RangeSlider) {
                        popupPip.dismiss()

                        slider.values
                            .getOrNull(0)
                            ?.roundToInt()
                            ?.let {
                                labeledPipThumbDrawable?.text = it.toString()
                                setAnswerValue(it)
                            }
                    }
                })
        }
    }

    private fun placePopupPip(
        rangeSlider: RangeSlider,
        value: Float,
        showIfHidden: Boolean = false
    ) {
        with(rangeSlider) {
            val location = IntArray(2)
            rangeSlider.getLocationOnScreen(location)

            val index = ((value * stepSize) - valueFrom).toInt()
            val x = location[0] + ticksCoordinates[index].toInt() - thumbRadius - pipPadding
            val y = location[1] - pipSize - pipPadding * 2

            if (popupPip.isShowing) {
                popupPip.update(x, y, pipSize + pipPadding * 2, pipSize + pipPadding * 2)
            } else if (showIfHidden) {
                popupPip.showAtLocation(viewBinding.root, Gravity.NO_GRAVITY, x, y)
            }
        }
    }

    override fun setValue(value: Int) {
        if (value == NO_VALUE_SELECTED) return
        val safeValue = value.coerceIn(choiceIndexes.first()..choiceIndexes.last()).toFloat()
        viewBinding.rangeSlider.setValues(safeValue)
        labeledPipThumbDrawable?.text = safeValue.toInt().toString()
    }

    override fun getWidget() = viewBinding

    private fun isRtl(view: View): Boolean {
        return ViewCompat.getLayoutDirection(view) == ViewCompat.LAYOUT_DIRECTION_RTL
    }

    companion object {
        const val NO_VALUE_SELECTED = -1
    }
}
