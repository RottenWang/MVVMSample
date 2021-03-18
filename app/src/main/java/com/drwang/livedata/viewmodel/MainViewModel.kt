package com.drwang.livedata.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.drwang.common.net.ApiFactory
import com.drwang.common.base.BaseViewModel
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class MainViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val name: SingleMutableLiveData<String> by lazy { SingleMutableLiveData<String>() }
    fun getName() {
        //如果正在执行 先取消
        job?.cancel()
        job = viewModelScope.launch() {
//            val data = withContext(Dispatchers.IO) {
            Log.d("thread", "thread = " + Thread.currentThread())
            //retrofit2.6.0 添加了对协程的支持 会默认切线程 不需要切到IO
            try {
                val appLogin = ApiFactory.getMyApi().appLogin("18811112222", "112222")

            }catch (throwable :Exception){
                if (throwable is ConnectException
                        || throwable is SocketTimeoutException
                        || throwable is UnknownHostException) {
//                apiException = ApiException(throwable, NETWORK_ERROR, "网络连接错误")

                }else if (throwable is HttpException) {
//                apiException = ApiException(throwable, HTTP_ERROR, "HTTP协议错误: ${throwable.code()}")

                } else if (throwable is JsonParseException
                        || throwable is JsonSyntaxException
                        || throwable is JsonIOException
                        || throwable is JSONException
                        || throwable is ParseException) {
//                apiException = ApiException(throwable, PARSE_ERROR, "Json 解析错误")
//                KLog.d("jsonerror",throwable.message)
//            } else if (throwable is ServerException) {
////                apiException = ApiException(throwable, throwable.code, throwable.msg)

                } else {
//                apiException = ApiException(throwable, UNKNOWN, "未知错误")
                }
            }

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