package com.delighted.sdk.view.question

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Paint.Align.CENTER
import android.graphics.Paint.Style.FILL
import android.graphics.Paint.Style.STROKE
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

@Suppress("DeprecatedCallableAddReplaceWith")
class LabeledPipDrawable(
    var text: String,
    private val knobColor: Int,
    private val knobRadius: Float,
    private val textColor: Int,
    textSize: Float,
    private val borderColor: Int? = null,
    private val borderStrokeWidth: Float = 2f
) : Drawable() {
    private val paint: Paint = Paint()

    override fun draw(canvas: Canvas) {
        val ctrX = bounds.centerX().toFloat()
        val ctrY = bounds.centerY().toFloat()
        val textCtrY = ((bounds.height() / 2) - ((paint.descent() + paint.ascent()) / 2))

        paint.style = FILL
        paint.isAntiAlias = true

        paint.color = knobColor
        paint.setShadowLayer(6f, 0f, 3f, 0x29000000)
        canvas.drawCircle(ctrX, ctrY, knobRadius, paint)

        paint.clearShadowLayer()
        if (borderColor != null) {
            paint.style = STROKE
            paint.strokeWidth = borderStrokeWidth
            paint.color = borderColor
            canvas.drawCircle(ctrX, ctrY, knobRadius - (borderStrokeWidth / 2), paint)
        }

        paint.style = FILL
        paint.color = textColor
        canvas.drawText(text, ctrX, textCtrY, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    private val constantState = CustomConstantState(this)

    override fun getConstantState(): ConstantState {
        return constantState
    }

    private class CustomConstantState(private val drawable: Drawable) : ConstantState() {
        override fun newDrawable(): Drawable = drawable

        override fun getChangingConfigurations(): Int = 0
    }

    init {
        paint.textSize = textSize
        paint.textAlign = CENTER
        paint.style = FILL
        paint.isAntiAlias = true
    }
}
