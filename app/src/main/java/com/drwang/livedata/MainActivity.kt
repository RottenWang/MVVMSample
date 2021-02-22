package com.drwang.livedata

import android.os.Bundle
import androidx.lifecycle.Observer
import com.drwang.livedata.base.BaseMVVMActivity
import com.drwang.livedata.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVVMActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = getViewModel(MainViewModel::class.java)
        viewModel.name.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                textView.text = t
            }
        })
        viewModel.getName()
    }

}