package com.drwang.common.ext

import com.drwang.common.net.result.AppException
import com.drwang.common.net.result.UIState

fun <T> T.getSuccessState(): UIState<T> {
    return UIState(true, this, null)
}

fun <T> AppException.getFailedState(): UIState<T> {
    return UIState(false, null, this.errorMsg)
}