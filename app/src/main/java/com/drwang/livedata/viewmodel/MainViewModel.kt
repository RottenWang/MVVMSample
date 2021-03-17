package com.drwang.livedata.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.drwang.common.net.Api
import com.drwang.livedata.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val name: SingleMutableLiveData<String> by lazy { SingleMutableLiveData<String>() }
    fun getName() {
        //如果正在执行 先取消
        job?.cancel()
        job = viewModelScope.launch(handler) {
//            val data = withContext(Dispatchers.IO) {
            Log.d("thread", "thread = " + Thread.currentThread())
            //retrofit 添加了对协程的支持 会默认切线程 不需要切到IO
            val appLogin = Api.myApi.appLogin("18811112222", "112222")
            Log.d("wang", "suspend finish")

//            }


        }
//        job.cancel()
//        doAsync {
//            SystemClock.sleep(1000)
//            name.postValue("testValue")
//        }
    }

    fun login() {


    }

}