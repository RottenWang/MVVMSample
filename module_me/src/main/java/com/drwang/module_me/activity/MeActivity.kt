package com.drwang.module_me.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.drwang.common.base.BaseMVVMActivity

class MeActivity : BaseMVVMActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
    override fun getLayoutId(): Int {
        return 0
    }
}