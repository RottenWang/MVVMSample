package com.drwang.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.ext.getVmClazz

abstract class BaseMVVMActivity<VM : BaseViewModel> : BaseActivity() {


    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        initObservable()
        initView()
        initData()

        //LiveData 返回值的用法 直接observe
//        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }

    abstract fun initObservable()

    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(BaseApp.instance)).get(getVmClazz(this))
    }

    open fun initData() {}

    open fun initView() {}

    //一般不用这两个方法
    open fun showLoading(loadMsg: String? = "") {}
    open fun dismissLoading() {}


}