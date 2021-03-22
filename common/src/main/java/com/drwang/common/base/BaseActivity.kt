package com.drwang.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (useBus()) {
            EventBus.getDefault().register(this)
        }


        //LiveData 返回值的用法 直接observe
//        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useBus()){
            EventBus.getDefault().unregister(this)
        }
    }
    abstract fun getLayoutId(): Int

    open fun useBus(): Boolean = false

}