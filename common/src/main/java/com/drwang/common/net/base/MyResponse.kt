package com.drwang.common.net.base

import com.drwang.common.net.result.BaseResponse

class MyResponse<T>(var code:Int,var msg:String,var result:T?) : BaseResponse<T>() {
    override fun toString(): String {
        return "code = $code msg = $msg"
    }

    override fun isSucces(): Boolean {
        return code == 1;
    }

    override fun getResponseData(): T? {
        return result
    }

    override fun getResponseCode(): Int {
        return code
    }

    override fun getResponseMsg(): String {
        return msg
    }
}