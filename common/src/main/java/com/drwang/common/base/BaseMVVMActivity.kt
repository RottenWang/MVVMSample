package com.drwang.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseMVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()

        //LiveData 返回值的用法 直接observe
//        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }

    open fun initData(){}

    open fun initView(){}

    abstract fun getLayoutId(): Int

    fun <T : BaseViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[clazz]
    }

}