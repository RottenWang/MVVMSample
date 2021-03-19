package com.drwang.common.net.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseApi {


    var okHttpClient = OkHttpFactory.client
    val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }


}