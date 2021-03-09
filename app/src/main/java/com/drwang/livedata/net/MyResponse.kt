package com.drwang.livedata.net

class MyResponse<T> {
    var code: Int? = null
    var msg: String? = null
    var result: T? = null
    override fun toString(): String {
        return "code = $code msg = $msg"
    }
}