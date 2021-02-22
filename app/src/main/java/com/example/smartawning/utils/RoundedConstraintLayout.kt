package com.example.smartawning.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class RoundedConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val backgroundRect : RectF = RectF(0f, 0f, 200f, 200f)
    private val backgroundCornerRadius = 36f
    private val backgroundPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas?) {
        backgroundRect.set(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
        canvas?.drawRoundRect(backgroundRect, backgroundCornerRadius , backgroundCornerRadius, backgroundPaint)
    }
}