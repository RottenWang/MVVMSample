package com.drwang.module_me.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.drwang.module_me.R

class MultiTouchView2(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val bitmap: Bitmap by lazy {
        getBitmap(400)
    }
    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    var offsetX = 0f
    var offsetY = 0f;
    var downX = 0f;
    var downY = 0f;
    var lastOffsetX = 0f;
    var lastOffsetY = 0f;
    fun getBitmap(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.abc, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.abc, options)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    var trackingPointerId: Int = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionIndex = event.actionIndex
        var pointerCount = event.pointerCount
        var sumX: Float = 0f;
        var sumY: Float = 0f;
        for (i in 0 until pointerCount) {
            if (event.actionMasked == MotionEvent.ACTION_POINTER_UP && actionIndex == i) {
                continue;
            }
            sumX += event.getX(i)
            sumY += event.getY(i)
        }
        if (event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
            pointerCount--;
        }
        var avaX = sumX / pointerCount
        var avaY = sumY / pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN ,MotionEvent.ACTION_POINTER_UP -> {
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                downX = avaX
                downY = avaY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = lastOffsetX + avaX - downX
                offsetY = lastOffsetY + avaY - downY
                invalidate()
            }
            MotionEvent.ACTION_UP-> {
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
            }
        }
        return true;
    }
}