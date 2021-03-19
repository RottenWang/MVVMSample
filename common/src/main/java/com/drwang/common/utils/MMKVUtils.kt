package com.drwang.common.utils

import com.tencent.mmkv.MMKV

object MMKVUtils {
    //token

    fun clear(){
        MMKV.defaultMMKV()?.clearAll()
    }
    var token by DelegatesExt.preference("token", "")
}