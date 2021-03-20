package com.drwang.aidl_server

import com.drwang.common.base.BaseApp

class AIDLServerApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
    }

    //注册子App初始化内容
    override fun appInit() {
        registerApplicationInit(AIDLServerInit::class.java)
    }
}