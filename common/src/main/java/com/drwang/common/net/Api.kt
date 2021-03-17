package com.drwang.common.net

import com.drwang.common.log.okHttpLog.HttpLoggingInterceptorM
import com.drwang.common.log.okHttpLog.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {


    val myApi by lazy {

        val loggingInterceptor = HttpLoggingInterceptorM(LogInterceptor("wang"))
//        val cacheInterceptor = CacheInterceptor(AppManager.currentActivity().applicationContext)
        loggingInterceptor.level = HttpLoggingInterceptorM.Level.BODY
        val build = OkHttpClient().newBuilder().addInterceptor(
                loggingInterceptor
        ).build()

        Retrofit.Builder().client(build).baseUrl("https://testerp.newrunway.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build().create(MyApi::class.java)
    }
}