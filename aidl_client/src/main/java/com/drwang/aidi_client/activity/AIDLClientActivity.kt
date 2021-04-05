package com.drwang.aidi_client.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.drwang.aidi_client.R
import com.drwang.aidl_server.aidl.Stub
import com.drwang.aidl_server.aidl.Person
import com.drwang.aidl_server.aidl.PersonManager
import com.drwang.common.base.BaseActivity
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast
import com.socks.library.KLog


class AIDLClientActivity : BaseActivity() {
    var isConnect = false;

    @Autowired(name = RouteField.name)
    @JvmField
    var name: String = ""

    private var personManger: PersonManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast(name)
        // 直接指定类 可以访问
//        val intent = Intent(this,ServerService::class.java)

        val intent = Intent()
        intent.setComponent(ComponentName("com.drwang.aidl_server","com.drwang.aidl_server.aidl.ServerService"))
//        intent.setClassName("com.drwang.aidl_server","com.drwang.aidl_server.ServerService")
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        Handler().postDelayed({       personManger?.addPerson(Person())
            val personList = personManger?.personList
            KLog.d("wangchen", personList)
        },1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }

    var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
                //正常关闭不会回调 被系统关闭才会回调
            KLog.d("wangchen", "un connected ")
        }

        override fun onServiceConnected(name2: ComponentName?, service: IBinder?) {
            KLog.d("wangchen", "connected ")
            isConnect = true
            personManger = Stub.asInterface(service)
            personManger?.addPerson(Person().apply {
                name = "lisi"
                age = 33
                })
            val personList = personManger?.getPersonList()
            val int = personManger?.int
            KLog.d("wangchen","size = " + personList + ",int = " + int)
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.aidl_client_activity_aidl
    }

}