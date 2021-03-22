package com.drwang.module_me.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.drwang.common.base.BaseApp
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.event.MyEvent
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast
import com.drwang.module_me.R
import com.drwang.module_me.viewmodel.MeViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.module_me_activity_me.*
import org.greenrobot.eventbus.EventBus

@Route(path = RouteClass.ME.module_me_me)
class MeActivity : BaseMVVMActivity<MeViewModel>() {

    @Autowired(name = RouteField.name)
    @JvmField
    var name :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast(name)

    }

    override fun initView() {
        EventBus.getDefault().post(MyEvent().apply { name = "testtest" })
        LiveEventBus.get("test").post(MyEvent().apply { name = "hahahahah" })
    }

    override fun getLayoutId(): Int {
        return R.layout.module_me_activity_me
    }

    override fun initObservable() {

    }
}