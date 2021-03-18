package com.drwang.common.net

object ApiFactory : BaseApi() {

    private var myApi: MyApi? = null

    fun getMyApi(): MyApi {
        if (myApi == null) {
            myApi =  mRetrofitBuilder.baseUrl("https://testerp.newrunway.com/").build().create(MyApi::class.java)
        }
        return myApi?:mRetrofitBuilder.baseUrl("https://testerp.newrunway.com/").build().create(MyApi::class.java)
    }
}