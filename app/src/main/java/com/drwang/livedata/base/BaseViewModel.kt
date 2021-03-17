package com.drwang.livedata.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val handler = CoroutineExceptionHandler { context, throwable ->
        Log.d("ERRORERROR",throwable.message)
        Log.d("ERRORERROR",Thread.currentThread().toString())
//            val apiException: ApiException
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

            viewModelScope.launch {
                
            }
    }
}