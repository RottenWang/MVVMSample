package com.drwang.common.utils

import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 */
class Preference<T>(val name: String, val default: T) : ReadWriteProperty<Any?, T> {
    private val defaultMMKV by lazy { MMKV.defaultMMKV() }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun <U> findPreference(name: String, default: U): U = defaultMMKV!!.run {
        val res = when (default) {
            is Long -> decodeLong(name, default)
            is String -> decodeString(name, default) as String
            is Int -> decodeInt(name, default)
            is Boolean -> decodeBool(name, default)
            is Float -> decodeFloat(name, default)
            is Double -> decodeDouble(name, default)
            is ByteArray -> decodeBytes(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = defaultMMKV?.run {
        when (value) {
            is Long -> encode(name, value)
            is String -> encode(name, value)
            is Int -> encode(name, value)
            is Boolean -> encode(name, value)
            is Float -> encode(name, value)
            is Double -> encode(name, value)
            is ByteArray -> encode(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
    }
}