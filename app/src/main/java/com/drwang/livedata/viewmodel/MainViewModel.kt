package com.drwang.livedata.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.drwang.common.net.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : BaseViewModel(application) {


    val name: SingleMutableLiveData<String> by lazy { SingleMutableLiveData<String>() }
    fun getName() {
        viewModelScope.launch(handler) {
            val data = withContext(Dispatchers.IO) {
                Log.d("thread", "thread = " + Thread.currentThread())
                //retrofit 添加了对协程的支持 会默认切线程 不需要切到IO
                async {
                    Api.myApi.appLogin("18811112222", "112222")

                }
                Api.myApi.appLogin("18811112222", "112222")

            }


        }
//        doAsync {
//            SystemClock.sleep(1000)
//            name.postValue("testValue")
//        }
    }

    fun login() {


    }

}