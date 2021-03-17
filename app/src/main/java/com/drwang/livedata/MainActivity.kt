package com.drwang.livedata

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.drwang.livedata.base.BaseMVVMActivity
import com.drwang.livedata.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVVMActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = getViewModel(MainViewModel::class.java)
        textView.text = "hello world"
        viewModel.name.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                textView.text = t
            }
        })
        viewModel.getName()
//        val client = OkHttpClient().newBuilder().build()
//        client.newCall(Request.Builder().build()).enqueue(object:Callback{
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//            }
//        })
        var objectAnimator = ObjectAnimator.ofObject(pv, "pointF", evaluater(), PointF(300f,300f))
        objectAnimator.duration = 1000
        objectAnimator.start()
    }

    inner class evaluater : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            var x = startValue.x + (endValue.x - startValue.x) * fraction
            var y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x, y)
        }

    }

}