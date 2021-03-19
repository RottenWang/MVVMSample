package com.drwang.livedata.viewmodel

import android.app.Application
import com.drwang.common.base.BaseViewModel
import com.drwang.common.ext.*
import com.drwang.common.model.User
import com.drwang.common.net.base.ApiFactory
import com.drwang.common.net.result.ResultState
import com.drwang.common.net.result.UIState
import kotlinx.coroutines.Job

class MainViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val name: SingleMutableLiveData<UIState<User>> by lazy { SingleMutableLiveData<UIState<User>>() }
    val name2: SingleMutableLiveData<ResultState<User>> by lazy { SingleMutableLiveData<ResultState<User>>() }
    fun getName() {
        //如果正在执行 先取消
        job?.cancel()
        job = request({ ApiFactory.getMyApi().appLogin("18811112222", "112222") }, {
            //success
            name.value = it.getSuccessStateData();
        }, {
            name.value = it.getFailedData()

        }, true, "loading")

    }

    fun login() {
        requestShowLoading({ ApiFactory.getMyApi().appLogin("18811112222", "112222") }, name2)

    }

    fun read() {
        launch({

        }, {

        }, {

        })
    }

}