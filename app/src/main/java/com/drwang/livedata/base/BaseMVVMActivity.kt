package com.drwang.livedata.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drwang.livedata.net.Api
import com.drwang.livedata.net.MyResponse

abstract class BaseMVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }


    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[clazz]
    }

}