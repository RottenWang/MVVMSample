package com.drwang.livedata

import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.ext.interceptLongClick
import com.drwang.common.ext.parseState
import com.drwang.common.model.User
import com.drwang.common.net.result.ResultState
import com.drwang.common.net.result.UIState
import com.drwang.common.utils.toast
import com.drwang.livedata.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVVMActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView.apply {
            enableAnimation(false)
            enableItemShiftingMode(false)
            enableShiftingMode(false)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){

            }
            true
        }

         bottomNavigationView.interceptLongClick(R.id.menu_main,R.id.menu_project,R.id.menu_system,R.id.menu_public,R.id.menu_me)
        mViewModel.login()

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
//                textView.text = it.data?.userName
            } else {
//                textView.text = it.errorMsg
            }
        })
        mViewModel.name2.observe(this, Observer<ResultState<User>> { it ->
            parseState(it, {
//                textView.text = it.userName
            }, {
//                textView.text = it.errorMsg
            }, {
                toast("loading")
            })
        })
    }

}