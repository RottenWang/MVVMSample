package com.drwang.module_me.activity

import android.os.Bundle
import com.drwang.common.base.BaseApp
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.utils.toast
import com.drwang.module_me.R

class MeActivity : BaseMVVMActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast(BaseApp.instance.getText())

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_me
    }
}