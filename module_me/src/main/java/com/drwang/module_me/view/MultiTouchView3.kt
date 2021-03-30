package com.drwang.module_me.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.forEach
import com.socks.library.KLog

class MultiTouchView3(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
                .apply {
                    strokeWidth = 10f
                    strokeJoin = Paint.Join.ROUND
                    strokeCap = Paint.Cap.ROUND
                    style = Paint.Style.STROKE
                }
    }
    val array: SparseArray<Path> by lazy {
        SparseArray<Path>()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        array.forEach { key, value ->
            canvas.drawPath(value, paint);
        }

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        KLog.e("wangchen","action = " + event.actionMasked)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                val pointerId = event.getPointerId(event.actionIndex)
                array.append(pointerId, path)

            }

            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until array.size()) {
                    //根据pointerIndex 拿到id 因为pointerIndex 是从0开始连续的 所以可以用array.size 获取其最大值,
                    var pointerId = -1;
                    pointerId = event.getPointerId(i)
                    //根据id拿path
                    val path = array.get(pointerId)
                    //将对应index的对应值 放到对应id的path中
                    path.lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
            //触发cancel时也要监听 不然会崩溃
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                if (event.actionMasked == MotionEvent.ACTION_UP || event.actionMasked == MotionEvent.ACTION_CANCEL) {
                    array.clear();
                } else {
                    val actionIndex = event.actionIndex
                    val pointerId = event.getPointerId(actionIndex)
                    array.remove(pointerId)
                }
                invalidate()
            }

        }
        return true;
    }
}