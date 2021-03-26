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
import com.drwang.module_me.leetcode.LeetCode
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
        LeetCode.main(arrayOf())
        val treeNode = LeetCode.TreeNode(1)
        treeNode.left = LeetCode.TreeNode(2)
        treeNode.right = LeetCode.TreeNode(3)
        treeNode.right.left = LeetCode.TreeNode(4)
        treeNode.right.right = LeetCode.TreeNode(5)
        val serialize = LeetCode().serialize(treeNode);
        KLog.d("wangchen","serialize = "+ serialize)
//        doAsync {
//            val coinChange = LeetCode().coinChange(intArrayOf(1, 2, 5), 100);
//            KLog.d("wangchen","change = " + coinChange)
//        }
        val myPow = LeetCode().myPow(2.0, 10);
        KLog.d("wangchen","pow = " + myPow)
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