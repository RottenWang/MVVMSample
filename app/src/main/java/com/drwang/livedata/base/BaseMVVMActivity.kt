package com.drwang.livedata.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseMVVMActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


      fun<T : ViewModel> getViewModel(clazz:Class<T>):T{
        return ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))[clazz]
    }

}