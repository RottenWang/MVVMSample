package com.drwang.common.base

import android.app.Application
import android.content.res.Configuration

abstract class BaseAppInit {

     lateinit var app: BaseApp
    fun setApplication(app: BaseApp) {
        this.app = app;
    }

    abstract fun onCreate()

    open fun onTerminate() {}

    open fun onLowMemory() {}

    open fun configurationChanged(configuration: Configuration) {}
}