package com.drwang.aidl_server

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.drwang.aidl_server.aidl.PersonManager
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast


class AIDLServerActivity : AppCompatActivity() {
    var isConnect = false;

    @Autowired(name = RouteField.name)
    @JvmField
    var name: String = ""

    private var personManger: PersonManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast(name)
//        val intent = Intent(this,ServerService::class.java)
//        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)

    }


}