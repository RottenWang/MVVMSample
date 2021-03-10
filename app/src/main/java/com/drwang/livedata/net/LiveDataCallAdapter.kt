package com.gg.net.widget

import androidx.lifecycle.LiveData
import com.drwang.livedata.net.MyResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R>(var observableType: Type) : CallAdapter<R, LiveData<R>> {
    override fun adapt(call: Call<R>): LiveData<R> {
        return object : LiveData<R>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.body())
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(null)
                        }
                    })
                }
            }


        }
    }

    override fun responseType(): Type {
        return observableType
    }
}