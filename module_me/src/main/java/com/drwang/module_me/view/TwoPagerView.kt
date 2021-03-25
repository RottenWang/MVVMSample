package com.drwang.module_me.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import com.socks.library.KLog
import kotlin.math.abs

class TwoPagerView(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {


    val velocityTracker: VelocityTracker by lazy {
        VelocityTracker.obtain()
    }
    val viewConfigration: ViewConfiguration by lazy {
        ViewConfiguration.get(getContext())
    }
    val minVelocity by lazy {
        viewConfigration.scaledMinimumFlingVelocity
    }
    val maxVelocity by lazy {
        viewConfigration.scaledMaximumFlingVelocity
    }
    val overScroller by lazy {
        OverScroller(context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childCount = childCount
        if (childCount != 2) {
            throw  IllegalArgumentException("only support two child view")
        }
        for (i in 0 until childCount) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        getChildAt(0).layout(0, 0, width, height);
        getChildAt(1).layout(width, 0, width * 2, height)
    }

    var downX: Float = 0f;
    var downY: Float = 0f;
    var downScrollX = 0;
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
////        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
////            velocityTracker.clear()
////        }
//        velocityTracker.addMovement(event)
//        when (event.actionMasked) {
//            MotionEvent.ACTION_DOWN -> {
//                downX = event.x
//                downY = event.y
//                downScrollX = scrollX
//            }
//            MotionEvent.ACTION_MOVE -> {
//                var dx = downX - event.getX()+downScrollX
//                if (abs(dx) > viewConfigration.scaledPagingTouchSlop){
//                    parent.requestDisallowInterceptTouchEvent(true)
//                    return true
//                }
//            }
//        }
        return super.onInterceptTouchEvent(event)

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                downScrollX = scrollX
            }
            MotionEvent.ACTION_MOVE -> {
                var scrollDistance = (downX - event.x).toInt() + downScrollX;
                scrollDistance = scrollDistance.coerceAtLeast(0).coerceAtMost(width)
                scrollTo(scrollDistance, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val vx = velocityTracker.getXVelocity(event.getPointerId(0))
                KLog.d("wangchen","xVelocity = $vx")
                //todo  这个速度不对
                val targetPage = if (abs(vx) < minVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    if (vx < 0) 1 else 0
                }
                val scrollDistance = if (targetPage == 1) width - scrollX else -scrollX
                //初始长度x, y  要移动多少距离
                overScroller.startScroll(scrollX, 0,scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true;

    }

    override fun computeScroll() {
        super.computeScroll()
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}