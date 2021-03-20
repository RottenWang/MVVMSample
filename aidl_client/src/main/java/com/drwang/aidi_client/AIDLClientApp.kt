package com.drwang.aidi_client

import com.drwang.common.base.BaseApp

class AIDLClientApp : BaseApp()  {
    override fun onCreate() {
        super.onCreate()
    }

    //注册子App初始化内容
    override fun appInit() {
        registerApplicationInit(AIDLClientInit::class.java)
    }
}