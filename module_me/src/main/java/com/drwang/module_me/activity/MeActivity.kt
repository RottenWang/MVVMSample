package com.drwang.module_me.activity

import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.drwang.common.base.BaseApp
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.event.MyEvent
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast
import com.drwang.module_me.R
import com.drwang.module_me.database.Person
import com.drwang.module_me.database.PersonDataBase
import com.drwang.module_me.viewmodel.MeViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import com.socks.library.KLog
import kotlinx.android.synthetic.main.module_me_activity_me.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync

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
//        EventBus.getDefault().post(MyEvent().apply { name = "testtest" })
//        LiveEventBus.get("test").post(MyEvent().apply { name = "hahahahah" })
//        doAsync {
            val personDb = Room.databaseBuilder(applicationContext, PersonDataBase::class.java, "persondb")
                    .build()
            val personDAO = personDb.personDAO()
        doAsync {
            personDAO.insertPerson(Person(3,"haha",30,"nima"))
            val queryPerson = personDAO.queryPerson()
            KLog.d("wangchen","queryPerson=$queryPerson")
        }

//        }

    }

    override fun getLayoutId(): Int {
        return R.layout.module_me_activity_me
    }

    override fun initObservable() {

    }
}