package com.drwang.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.ext.getVmClazz

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        //LiveData 返回值的用法 直接observe
//        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }

    abstract fun getLayoutId(): Int

}