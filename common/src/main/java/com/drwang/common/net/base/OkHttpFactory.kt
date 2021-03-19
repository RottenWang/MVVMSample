package com.drwang.common.net.base

import com.drwang.common.log.okHttpLog.HttpLoggingInterceptorM
import com.drwang.common.log.okHttpLog.LogInterceptor
import com.drwang.common.net.utils.HttpUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 */

object OkHttpFactory {
    private var CONNECT_TIMEOUT_SECONDS = 20L
    private var READ_TIMEOUT_SECONDS = 60L
    private var WRITE_TIMEOUT_SECONDS = 20L

    val client: OkHttpClient by lazy { create() }

    fun create(customInterceptor: Interceptor? = null, enableLog: Boolean = true, tag: String = "http"): OkHttpClient {
        val sslParams = HttpUtils.getSslSocketFactory(null, null, null)
        val loggingInterceptor = HttpLoggingInterceptorM(LogInterceptor(tag))
//        val cacheInterceptor = CacheInterceptor(AppManager.currentActivity().applicationContext)
        loggingInterceptor.level = HttpLoggingInterceptorM.Level.BODY
//        val cacheInterceptor = CacheInterceptor(AppManager.currentActivity().applicationContext)

        return OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .apply {
                    if (customInterceptor != null)
                        addInterceptor(customInterceptor)
                }
                .apply {
                    if (enableLog)
                        addInterceptor(loggingInterceptor)
                }
                .apply {
//                    addInterceptor(cacheInterceptor)
                }
                .build()
    }
}