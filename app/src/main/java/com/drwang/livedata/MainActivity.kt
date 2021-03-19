package com.drwang.livedata

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.ext.launch
import com.drwang.common.ext.parseState
import com.drwang.common.model.User
import com.drwang.common.net.result.ResultState
import com.drwang.common.net.result.UIState
import com.drwang.common.utils.MMKVUtils
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast
import com.drwang.livedata.viewmodel.MainViewModel
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVVMActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView.text = "hello world"

//        viewModel.getName()
//        mViewModel.getName()
//        mViewModel.getName()
        mViewModel.login()
        pv.setOnClickListener {
            ARouter.getInstance().build(RouteClass.ME.module_me_me).withString(RouteField.name, "Test Name").navigation()
        }
        mViewModel.launch({
            for (i in 0..100) {
                MMKVUtils.token = "${i.toString()}"
                KLog.d("wangchen", "token = ${MMKVUtils.token}")
            }

        }, {

        }, {

        })


        //        val client = OkHttpClient().newBuilder().build()
//        client.newCall(Request.Builder().build()).enqueue(object:Callback{
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//            }
//        })
        var objectAnimator = ObjectAnimator.ofObject(pv, "pointF", evaluater(), PointF(300f, 300f))
        objectAnimator.duration = 1000
        objectAnimator.start()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    inner class evaluater : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            var x = startValue.x + (endValue.x - startValue.x) * fraction
            var y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x, y)
        }

    }

    override fun initObservable() {
        mViewModel.name.observe(this, Observer<UIState<User>> {
            if (it.success) {
                textView.text = it.data?.userName
            } else {
                textView.text = it.errorMsg
            }
        })
        mViewModel.name2.observe(this, Observer<ResultState<User>> { it ->
            parseState(it, {
                textView.text = it.userName
            }, {
                textView.text = it.errorMsg
            }, {
                toast("loading")
            })
        })
    }

}