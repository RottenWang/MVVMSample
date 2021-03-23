package com.drwang.livedata

import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.event.MyEvent
import com.drwang.common.ext.interceptLongClick
import com.drwang.common.ext.parseState
import com.drwang.common.model.User
import com.drwang.common.net.result.ResultState
import com.drwang.common.net.result.UIState
import com.drwang.common.route.MeService
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.toast
import com.drwang.livedata.viewmodel.MainViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast

class MainActivity : BaseMVVMActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView.apply {
            enableAnimation(false)
            enableItemShiftingMode(false)
            enableShiftingMode(false)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {

            }
            true
        }

        bottomNavigationView.interceptLongClick(R.id.menu_main, R.id.menu_project, R.id.menu_system, R.id.menu_public, R.id.menu_me)
//        mViewModel.login()
        mViewModel.getName()

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

    override fun useBus(): Boolean {
        return false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventtest(event: MyEvent) {
        toast(event.name!!)
    }

    override fun onResume() {
        super.onResume()
//        ARouter.getInstance().build(RouteClass.ME.module_me_me).navigation()
//        mViewModel.login()
        val navigation = ARouter.getInstance().build(RouteClass.ME.meservice).navigation()
        if (navigation is MeService){
            toast("${navigation.testFun(3)}")
        }
    }

    override fun onPause() {
        super.onPause()
//        mViewModel.login()
    }

    override fun initObservable() {

        //测试事件
//        LiveEventBus.get("test",MyEvent::class.java).observe(this, Observer {
//            if (it is MyEvent) {
//                toast(it.name!!)
//            }
//        })
        mViewModel.name.observe(this, Observer<UIState<User>> {
            if (it.success) {
                KLog.d("wangchen","success ${it.data}")
//                textView.text = it.data?.userName
            } else {
//                textView.text = it.errorMsg
            }
        })
        mViewModel.name2.observe(this, Observer<ResultState<User>> { it ->
            parseState(it, {
                lifecycle.currentState
                KLog.d("wangchen", "result ok +  lifecycle.currentState = ${lifecycle.currentState}")
//                textView.text = it.userName
            }, {
//                textView.text = it.errorMsg
            }, {
                toast("loading")
            })
        })
    }

}