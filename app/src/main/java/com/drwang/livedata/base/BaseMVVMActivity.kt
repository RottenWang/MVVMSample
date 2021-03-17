package com.drwang.livedata.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.drwang.livedata.R

abstract class BaseMVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        //LiveData 返回值的用法 直接observe
//        Api.myApi.appLoginLiveData("18811112222", "112222").observe(this, Observer<MyResponse<Any>> { })
    }


    fun <T : BaseViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[clazz]
    }

}