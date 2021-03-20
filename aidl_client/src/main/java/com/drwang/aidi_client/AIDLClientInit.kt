package com.drwang.aidi_client

import android.util.Log
import com.drwang.common.base.BaseApp
import com.drwang.common.base.BaseAppInit

open class AIDLClientInit :BaseAppInit() {
    override fun onCreate() {
       Log.d("APP INSTANCE",  "AIDLServerInit")
    }

}