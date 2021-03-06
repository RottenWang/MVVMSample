package com.drwang.common.base

import android.content.res.Configuration
import android.os.Build
import android.webkit.WebView
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.drwang.common.net.base.ApiFactory
import com.drwang.common.net.base.MyResponse
import com.drwang.common.net.base.OkHttpFactory
import com.drwang.common.utils.*
import com.tencent.mmkv.MMKV
import okhttp3.Interceptor
import okhttp3.ResponseBody.Companion.toResponseBody

abstract class BaseApp : MultiDexApplication() {
    companion object {
        lateinit var instance: BaseApp
    }

    private val classInitList: ArrayList<Class<out BaseAppInit>> by lazy {
        ArrayList<Class<out BaseAppInit>>()
    }
    private val appInitList: ArrayList<BaseAppInit> by lazy {
        ArrayList<BaseAppInit>()
    }

    init {
        instance = this
    }

    var token: String by DelegatesExt.preference(AppConstant.TOKEN, "")
    var APP_LOG_TAG = "MVVMSample"
    val beta: Boolean by lazy { true }
    override fun onCreate() {
        super.onCreate()
        //为每个进程初始化一个webview的存储路径
        initWebView()
        if (applicationInfo.packageName == curProcessName()) {
            initialize()

        }
    }

    private fun initialize() {
        appInit()
        initCreate()
        initNet()
        initARouter()
        initMMKV()
    }

    private fun initMMKV() {
        val initialize = MMKV.initialize(this)
    }

    private fun initARouter() {
        if (isApkInDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)
    }

    private fun initCreate() {
        classInitList.forEach {
            try {
                val appInit = it.newInstance()
                appInitList.add(appInit)
                appInit.setApplication(this)
                appInit.onCreate()
            } catch (e: Exception) {

            }
        }
    }

    abstract fun appInit()

    fun registerApplicationInit(classInit: Class<out BaseAppInit>) {
        classInitList.add(classInit)
    }

    override fun onTerminate() {
        super.onTerminate()
        appInitList.forEach {
            it.onTerminate()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        appInitList.forEach {
            it.onLowMemory()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        appInitList.forEach {
            it.configurationChanged(newConfig)
        }
    }

    private fun initNet() {
        ApiFactory.okHttpClient = OkHttpFactory.create(Interceptor {
            val originRequest = it.request()
            // 添加 header 以及公共的 GET 参数
            val newRequest = originRequest.newBuilder()
                    .addHeader("Authorization", "Bearer 12312312")
                    .addHeader("source", "android")
                    .addHeader("version", versionName())
//                        .addHeader("currentUserId", getUser()?.userId ?: "")
//                        .addHeader("currentRoleId", getUser()?.getUserRoleId() ?: "")
                    .url(originRequest.url.newBuilder()
//                            .addQueryParameter("uid", LocalUser.userToken?.user_id?:"unlogin")
//                            .addQueryParameter("token", LocalUser.userToken?.token?:"")
                            .build()
                    ).build()

            /** 处理不规范的返回值
             *  <-- 400 Bad Request
             *  {
             *      "code": 2,
             *      "msg": "密码错误",
             *      "result": []             // 应该返回 空对象{}, 否则 Json 解析异常
             *  }
             */
            /** 处理不规范的返回值
             *  <-- 400 Bad Request
             *  {
             *      "code": 2,
             *      "msg": "密码错误",
             *      "result": []             // 应该返回 空对象{}, 否则 Json 解析异常
             *  }
             */
            val response = it.proceed(newRequest)
            response.newBuilder()
                    .apply {
//                       KLog.w("code", response.code())
                        if (response.code == 401) {
                            //处理token问题
                            //重新创建一个新的responseBody 并返回 需要将code改为200
//                            val originBody = response.body
//                            val response1 = MyResponse<Any>(1, "hahahah", null)
//                            response1.code = 1
//                            response1.msg = "hahahah"
//                            val toJson = GsonUtil.toJson(response1)
//                            this.body(toJson.toResponseBody(originBody?.contentType()))
//                            this.code(200)
                            //deprecated
//                                this.body(ResponseBody.create(originBody?.contentType(), GsonUtil.toJson(response1)))
//                                Handler(Looper.getMainLooper()).post {
//                                    //接口请求时可能正在展示loadingDialog  隐藏dialog 避免崩溃
//                                    LoadUtils.hidden()
//                                    LoginUtil.clearUserInfo(this@App)
//                                }
//                            return@apply
                        } else {
                            var isList = false
                            val authorization = response.headers.get("Authorization")
                            if (!authorization.isNullOrBlank()) {
                                MMKVUtils.token = authorization.toString()
                            }
                            val originBody = response.body
                            this.body(originBody)
                        }
                    }
//                    .apply {
//
////                        KLog.w("code----", response.code)
////                        this.code(if (response.code in 400..500) 200 else response.code)
//                    }
                    .build()
        }, enableLog = beta, tag = APP_LOG_TAG)

    }

    private fun initWebView() {
        //P以上设置对应进行的webview存储路径 否则会报错
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName = getProcessName()
            if (packageName != processName) {
                WebView.setDataDirectorySuffix(processName)
            }
        }
    }

    fun getText(): String {
        return "abcd"
    }
}