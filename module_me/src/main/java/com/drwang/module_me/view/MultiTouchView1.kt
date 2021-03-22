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
import com.socks.library.KLog

class MultiTouchView1(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)

                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                downX = event.getX()
                downY = event.getY()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndex)
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
            }
            MotionEvent.ACTION_MOVE -> {
                val pointerIndex = event.findPointerIndex(trackingPointerId)
                offsetX = lastOffsetX + (event.getX(pointerIndex) - downX)
                offsetY = lastOffsetY + (event.getY(pointerIndex) - downY)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {

                KLog.e("exception", "ACTION_UP")
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
            }
            MotionEvent.ACTION_POINTER_UP -> {
                KLog.e("exception", "ACTION_POINTER_UP")
                //拿到抬起的index
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                if (pointerId == trackingPointerId) {
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        //抬起的是当前的手指
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    //不能保存这个newIndex  要保存id  因为抬起后 index可能会发生变化 这个时候用index获取就会出问题
                    //使用event.findPointerIndex(trackingPointerId) 获取变化后的index
                    trackingPointerId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    lastOffsetX = offsetX;
                    lastOffsetY = offsetY;
                }


            }
        }
        return true;
    }
}