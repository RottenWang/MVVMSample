package com.drwang.module_me

import com.drwang.common.base.BaseApp

class MeApp : BaseApp()  {
    override fun onCreate() {
        super.onCreate()
    }

    //注册子App初始化内容
    override fun appInit() {
        registerApplicationInit(MeAppInit::class.java)
    }
}