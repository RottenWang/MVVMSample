package com.drwang.module_me.route

import com.alibaba.android.arouter.facade.annotation.Route
import com.drwang.common.route.MeService
import com.drwang.common.utils.RouteClass


@Route(path = RouteClass.ME.meservice)
class MeServiceImpl  : MeService {
    override fun testFun(x: Int): Int {
        return x*x;
    }

}