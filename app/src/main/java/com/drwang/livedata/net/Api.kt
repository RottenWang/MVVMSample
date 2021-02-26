package com.drwang.livedata.net

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {


    val myApi  by lazy {
        Retrofit.Builder().client(OkHttpClient()).baseUrl("https://testerp.newrunway.com/").addConverterFactory(GsonConverterFactory.create())
                .build().create(MyApi::class.java)
    }
}