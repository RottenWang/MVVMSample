package com.drwang.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.ext.getVmClazz

abstract class BaseMVVMFragment<VM : BaseViewModel> : Fragment() {


    lateinit var mViewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        mViewModel = createViewModel()
        initObservable()
        initView()
        initData()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun initObservable()

    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(BaseApp.instance)).get(getVmClazz(this))
    }

    open fun initData() {}

    open fun initView() {}

    abstract fun getLayoutId(): Int

    //一般不用这两个方法
    open fun showLoading(loadMsg: String? = "") {}
    open fun dismissLoading() {}


}