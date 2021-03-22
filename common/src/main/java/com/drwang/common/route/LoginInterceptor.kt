package com.drwang.common.route

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.ToastUtil

@Interceptor(priority = 8, name = "登录拦截器")
class LoginInterceptor : IInterceptor {
    var context: Context? = null;
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        //拦截特定的path 然后可以进行提示 或者跳转登录页面等
        if (postcard.path == RouteClass.ME.module_me_me) {
            ToastUtil.showToast(context, "请先登录")
            callback.onInterrupt(null)
        } else {
            callback.onContinue(postcard)
        }
    }

    override fun init(context: Context) {
        this.context = context;
    }
}