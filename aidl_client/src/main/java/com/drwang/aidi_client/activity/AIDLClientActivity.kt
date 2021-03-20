package com.drwang.aidi_client.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.drwang.aidi_client.R
import com.drwang.aidl_server.aidl.BinderObj
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
        val intent = Intent("com.drwang.aidl_server.serverservice")
        intent.setPackage("com.drwang.aidl_server")
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }

    var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

            KLog.d("wangchen", "un connected ")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            KLog.d("wangchen", "connected ")
            isConnect = true
            personManger = BinderObj.asInterface(service)
            val personList = personManger?.getPersonList()
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.aidl_client_activity_aidl
    }

}