package com.drwang.module_me.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class PointView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    var point = PointF(100f, 100f)
//        get() {
//            return field
//        }
//        set(value) {
//            field = value
//            invalidate()
//        }
    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            setColor(Color.RED)
                    strokeWidth = 20f
        }
    }
    fun setPointF(pointF: PointF){
        this.point = pointF
        invalidate()
    }
    fun getPointF(): PointF {
        return point
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(point.x, point.y, paint)
    }
}