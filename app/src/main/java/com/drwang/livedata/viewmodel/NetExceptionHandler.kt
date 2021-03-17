package com.drwang.livedata.viewmodel

import android.util.Log
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineExceptionHandler
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

//TODO 存在线程安全问题
class NetExceptionHandler {

    companion object {
        val instance: NetExceptionHandler by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetExceptionHandler()
        }
    }

    val handler by lazy {
        CoroutineExceptionHandler { context, throwable ->
            Log.d("ERRORERROR", throwable.message)
            Log.d("ERRORERROR", Thread.currentThread().toString())
//            val apiException: ApiException
            if (throwable is ConnectException
                    || throwable is SocketTimeoutException
                    || throwable is UnknownHostException) {
//                apiException = ApiException(throwable, NETWORK_ERROR, "网络连接错误")

            } else if (throwable is HttpException) {
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
    }

}