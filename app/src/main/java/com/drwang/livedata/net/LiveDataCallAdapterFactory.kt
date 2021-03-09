package com.gg.net.widget

import androidx.lifecycle.LiveData
import com.drwang.livedata.net.MyResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory :CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val responseType: Type

        if (getRawType(returnType) != LiveData::class.java) {
            throw IllegalStateException("return type must be parameterized")
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        responseType = if (rawObservableType == MyResponse::class.java) {
            if (observableType !is ParameterizedType) {
                throw IllegalArgumentException("Response must be parameterized")
            }
            getParameterUpperBound(0, observableType)
        } else {
            observableType
        }
        return LiveDataCallAdapter<Any>(responseType)
    }

}