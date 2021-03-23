package com.drwang.common.net.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory  {


    var okHttpClient = OkHttpFactory.client
    val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }

    private var myApi: MyApi? = null

    fun getMyApi(): MyApi {
        if (myApi == null) {
            myApi =  mRetrofitBuilder.baseUrl("https://testerp.newrunway.com/").build().create(MyApi::class.java)
        }
        return myApi
                ?:mRetrofitBuilder.baseUrl("https://testerp.newrunway.com/").build().create(MyApi::class.java)
    }
}