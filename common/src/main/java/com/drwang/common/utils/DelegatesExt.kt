package com.drwang.common.utils

import android.content.Context

/**
 *
 */
object DelegatesExt {

    fun <T : Any> preference(name: String, default: T)
            = Preference(name, default)

}