package com.drwang.common.ext

import com.drwang.common.net.result.AppException
import com.drwang.common.net.result.UIState

fun <T> T.getSuccessStateData(): UIState<T> {
    return UIState(true, this, null)
}

fun <T> AppException.getFailedData(): UIState<T> {
    this
    return UIState(false, null, this.errorMsg)
}