package com.drwang.livedata.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.drwang.common.base.BaseViewModel
import com.drwang.common.ext.getFailedState
import com.drwang.common.ext.getSuccessState
import com.drwang.common.ext.request
import com.drwang.common.model.User
import com.drwang.common.net.base.ApiFactory
import com.drwang.common.net.result.UIState
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class MainViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val name: SingleMutableLiveData<UIState<User>> by lazy { SingleMutableLiveData<UIState<User>>() }
    fun getName() {
        //如果正在执行 先取消
        job?.cancel()
         job = request({ ApiFactory.getMyApi().appLogin("18811112222", "112222") }, {
            //success
            name.value = it.getSuccessState();
        }, {
            name.value = it.getFailedState()

        },true,"loading")

    }

    fun login() {


    }

}