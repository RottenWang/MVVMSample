package com.drwang.common.net.result

data class UIState<T>(var success: Boolean, var data: T? = null, var errorMsg: String? = null)