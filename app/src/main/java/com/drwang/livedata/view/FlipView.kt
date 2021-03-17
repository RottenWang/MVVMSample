package com.drwang.livedata.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.drwang.livedata.R

class FlipView(context: Context, attributes: AttributeSet?) : View(context, attributes) {


    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    val camera by lazy {
        Camera().apply {
            setLocation(0f, 0f, 6 * resources.displayMetrics.density)
        }
    }
    val bitmap by lazy {
        getBitmap(400)
    }

    val deltaX = 100f
    val deltaY = 100f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var bitmapWidth = bitmap.width
        var bitmapHeight = bitmap.height
        val translateX = deltaX + bitmapWidth / 2
        val translateY = deltaY + bitmapHeight / 2
        canvas.save()
        //绘制上半部分
        canvas.translate(translateX, translateY)
        canvas.rotate(-30f)
//        canvas.rotate(-20f)
        camera.save()
        camera.rotateX(0f)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-bitmapWidth.toFloat(), -bitmapHeight.toFloat(), bitmapWidth.toFloat(), 0f)
//        canvas.rotate(20f)
        canvas.rotate(30f)
        canvas.translate(-translateX, -translateY)
        canvas.drawBitmap(bitmap, deltaX, deltaY, paint)
        canvas.restore()
//        canvas.clipRect(deltaX, deltaY + bitmapHeight / 2, deltaX + bitmapWidth, deltaY + bitmapHeight)
        //绘制下半部分
        canvas.save()
        canvas.translate(translateX, translateY)
        canvas.rotate(-30f)
        camera.save()
        camera.rotateX(-45f)
        camera.applyToCanvas(canvas)
        camera.restore()
        //旋转后 矩形区域变大 所以需要截取的范围也变大 下面这行代码会丢失图形
//        canvas.clipRect(-bitmapWidth / 2f, 0f, bitmapWidth / 2f, bitmapHeight / 2f)
        canvas.clipRect(-bitmapWidth.toFloat(), 0f, bitmapWidth.toFloat(), bitmapHeight.toFloat())
        canvas.rotate(30f)
        canvas.translate(-translateX, -translateY)
        canvas.drawBitmap(bitmap, deltaX, deltaY, paint)
        canvas.restore()
    }


    fun getBitmap(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.default_bg, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.default_bg, options)
    }

    override fun postOnAnimation(action: Runnable?) {
        super.postOnAnimation(action)
    }
}