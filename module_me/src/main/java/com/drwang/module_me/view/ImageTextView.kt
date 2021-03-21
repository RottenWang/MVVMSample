package com.drwang.module_me.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.drwang.module_me.R

class ImageTextView(context: Context, attributes: AttributeSet?) : View(context, attributes) {


    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, Resources.getSystem().displayMetrics)
        }
    }
    val texts by lazy {
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }
    val bitmap by lazy {
        getBitmap(400)
    }

    val deltaX = 300f
    val deltaY = 200f
    val deltaTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, Resources.getSystem().displayMetrics)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        resetValues()
        val toCharArray = texts.toCharArray()
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val bitmapTop = deltaY
        val bitmapBottom = deltaY + bitmapHeight
        val bitmapLeft = deltaX
        val bitmapRight = deltaX + bitmapWidth
        val fontMetrics = paint.fontMetrics
        var y = (fontMetrics.bottom - fontMetrics.top) / 2f + deltaTop
        var index = 0;
        var line = 0;
        val measureText = paint.measureText(texts)
        val rect = Rect()
        paint.getTextBounds(texts, 0, texts.length, rect)
        val length = rect.right - rect.left;
        //measureText 和length的差值为不同大小文字前面的间距? 可以绘制时减去这个间距以针对不同大小文字做对齐
        var floatArray = FloatArray(1)
        var currentY = y + line * paint.fontSpacing
        var textStartY = currentY - y;
        var drawLeft = false
        var drawRight = false

        var count = if (currentY - y in bitmapTop..bitmapBottom || currentY in bitmapTop..bitmapBottom) {
            //到图片位置了
            if (!drawLeft) {
                drawLeft = true
                paint.breakText(toCharArray, index, texts.length - index, bitmapLeft, null)
            } else if (!drawRight) {
                drawRight = true

                paint.breakText(toCharArray, index, texts.length - index, width - bitmapRight, null)
            } else {
                0
            }
        } else {
            paint.breakText(toCharArray, index, texts.length - index, width.toFloat(), null)
        }
//        var count = paint.breakText(toCharArray, index, texts.length - index, width.toFloat(), floatArray)
        //绘制文字数量大于0 或者已经绘制的文字小于总长度时 继续判断
        while (count > 0 || index < texts.length) {
            if (drawRight) {
                //从图片右边开始绘制文字
                canvas.drawText(toCharArray, index, count, bitmapRight, currentY, paint);
            } else {
                //从屏幕左边开始绘制文字
                canvas.drawText(toCharArray, index, count, 0f, currentY, paint);
            }
            if (drawLeft == drawRight) {
                //一行绘制完毕后 下一行
                line++
                //左右相等 因此绘制了左也就是绘制了右  重置左右绘制状态
                if (drawLeft) {
                    drawLeft = false
                    drawRight = false
                }
            }
            index += count
            currentY = y + line * paint.fontSpacing
            //判断文字的上下两边是否在图片的范围内 存一即进行判断
            if (currentY - y in bitmapTop..bitmapBottom || currentY in bitmapTop..bitmapBottom) {
                //到图片位置了
                if (!drawLeft) {
                    //左边没画 看左边能绘制多少个字 可能是0
                    count = paint.breakText(toCharArray, index, texts.length - index, bitmapLeft, null)
                    drawLeft = true
                } else if (!drawRight) {
                    //右边没画 看右边能绘制多少个字 可能是0
                    count = paint.breakText(toCharArray, index, texts.length - index, width - bitmapRight, null)
                    drawRight = true
                }
            } else {
                //未在图片范围内 直接绘制一整行内容
                count = paint.breakText(toCharArray, index, texts.length - index, width.toFloat(), null)
            }
//            count = 0

        }
        canvas.drawBitmap(bitmap, deltaX, deltaY, paint)
    }

    private fun resetValues() {

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