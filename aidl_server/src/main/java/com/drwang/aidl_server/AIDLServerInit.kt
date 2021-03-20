package  com.drwang.aidl_server

import android.util.Log
import com.drwang.common.base.BaseAppInit

open class AIDLServerInit : BaseAppInit() {
    override fun onCreate() {
        Log.d("APP INSTANCE", "AIDLServerInit")
    }

}