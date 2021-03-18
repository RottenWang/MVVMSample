package com.drwang.module_me

import android.util.Log
import com.drwang.common.base.BaseApp
import com.drwang.common.base.BaseAppInit

open class MeAppInit :BaseAppInit() {
    override fun onCreate() {
       Log.d("APP INSTANCE",  "MeAppInit")
    }

}