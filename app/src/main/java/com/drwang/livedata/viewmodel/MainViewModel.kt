package com.drwang.livedata.viewmodel

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun getName() {
        doAsync {
            SystemClock.sleep(1000)
            name.postValue("testValue")
        }
    }
}