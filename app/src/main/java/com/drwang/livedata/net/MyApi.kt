package com.drwang.livedata.net

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {


    /**
     * app登录
     */
    @FormUrlEncoded
    @POST("controller/loginInfo")
    suspend fun appLogin(@Field("accountName") accountName: String,
                         @Field("accountPassword") accountPassword: String,
                         @Field("applicationId") applicationId: String = "180813095600238",
                         @Field("loginUrl") loginUrl: String = "/controller/NewLoginJson",
                         @Field("exceptionUrl") exceptionUrl: String = "/controller/NewLoginJson",
                         @Field("logoutUrl") logoutUrl: String = "/controller/NewLoginJson"
    ): Any

    /**
     * app登录
     */
    @FormUrlEncoded
    @POST("controller/loginInfo")
    fun appLoginLiveData(@Field("accountName") accountName: String,
                         @Field("accountPassword") accountPassword: String,
                         @Field("applicationId") applicationId: String = "180813095600238",
                         @Field("loginUrl") loginUrl: String = "/controller/NewLoginJson",
                         @Field("exceptionUrl") exceptionUrl: String = "/controller/NewLoginJson",
                         @Field("logoutUrl") logoutUrl: String = "/controller/NewLoginJson"
    ): LiveData<MyResponse<Any>>
}