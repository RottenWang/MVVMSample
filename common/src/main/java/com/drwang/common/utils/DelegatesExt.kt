package com.drwang.common.utils

import android.content.Context

/**
 *
 */
object DelegatesExt {

    fun <T : Any> preference(context: Context, name: String, default: T)
            = Preference(context, name, default)

    fun clearPreference(context: Context) {
        Preference(context, "")
    }
}