package com.drwang.common.route

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.drwang.common.utils.RouteClass

interface MeService :IProvider {
    override fun init(context: Context?) {

    }
    fun testFun(x:Int):Int

}