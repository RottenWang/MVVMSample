package com.drwang.livedata

import com.drwang.common.base.BaseApp
import com.drwang.module_me.MeAppInit

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()

    }

    override fun appInit() {
        //TODO 所有组件自定义的APP初始化方法类在此注册
        registerApplicationInit(MeAppInit::class.java)
    }
}