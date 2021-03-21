package com.drwang.module_me.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.drwang.module_me.R

class ScaleAbleImageView : View {


    val bitmap: Bitmap by lazy {
        getBitmap(400)
    }
    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    var transX: Float = 0f;
    var transY: Float = 0f;
    var currX: Float = 0f;
    var currY: Float = 0f
    var smallScale: Float = 0f;
    var bigScale: Float = 0f;
    var isBig: Boolean = false;
    val OVER_SIZE = 1.5f
    var minWidthTrans:Float = 0f
    var minHeightTrans:Float = 0f;
    val scroller by lazy {
        OverScroller(context)
    }

    val oa by lazy {
        ObjectAnimator.ofFloat(this@ScaleAbleImageView, "Fraction", 0f, 1f)
    }
    val detector: GestureDetectorCompat by lazy {
        GestureDetectorCompat(context, TapGestureDetector(context)).apply {
        }
    }
    private fun fixOffsets() {

        //设定边界
        currX = Math.max(-minWidthTrans,currX)
        currX = Math.min(minWidthTrans,currX)
        currY = Math.max(-minHeightTrans,currY)
        currY = Math.min(minHeightTrans,currY)
    }

    var fraction: Float = 0f
        get() {
            return field
        }
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        setBackgroundColor(Color.RED)
    }

    fun getBitmap(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.abc, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.abc, options)
    }

    private val pointF by lazy {
        PointF().apply {
            x = width/2f;
            y = height/2f;
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        transX = (w - bitmap.width) / 2f;
        transY = (h - bitmap.height) / 2f;
        if (bitmap.width / w > bitmap.height / h) {
            smallScale = h / bitmap.height.toFloat();
            bigScale = w / bitmap.width.toFloat() * OVER_SIZE;
        } else {
            bigScale = h / bitmap.height.toFloat() * OVER_SIZE;
            smallScale = w / bitmap.width.toFloat();
        }
        minWidthTrans = (bitmap.width * bigScale - w )/ 2f
        minHeightTrans = (bitmap.height * bigScale - h )/ 2f
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return detector.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var currentScale = smallScale + (bigScale - smallScale) * fraction;
        //乘以fraction 缩小时 就会回到居中位置 因为translate的值渐渐变为0
        canvas.translate(currX * fraction, currY * fraction);
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, transX, transY, paint)
    }


    inner class MyRunable :Runnable{
        override fun run() {
            if (scroller.computeScrollOffset()){
                currX = scroller.currX.toFloat()
                currY = scroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }
    }
    private val runnable by lazy { MyRunable() }
    inner class TapGestureDetector(context: Context) : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBig){
                scroller.fling(currX.toInt(),currY.toInt(),velocityX.toInt(),velocityY.toInt(),-minWidthTrans.toInt(),minWidthTrans.toInt(),-minHeightTrans.toInt(),minHeightTrans.toInt() ,100,100)
                postOnAnimation(runnable)
            }
            return false
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (isBig) {
                currX -= distanceX
                currY -= distanceY
                fixOffsets()
            }
            invalidate()
            return false
        }
        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBig = !isBig
            if (isBig) {
                currX = (e.x -width/2) - (e.x -width/2) * bigScale / smallScale
                currY= (e.y -height/2) - (e.y-height/2) * bigScale / smallScale
                fixOffsets()
                oa.start()
            } else {
                oa.reverse()
            }
            return false

        }
    }
}