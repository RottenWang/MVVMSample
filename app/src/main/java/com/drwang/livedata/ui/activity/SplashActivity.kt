package com.drwang.livedata.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.drwang.common.base.BaseActivity
import com.drwang.livedata.MainActivity
import com.drwang.livedata.R

/**
 *  Creator : GG
 *  Time    : 2017/11/13
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain : 闪屏页
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }

        hideBottomUIMenu()
        Handler().postDelayed({
//            val intent: Intent = if (App.instance.getUser() == null)

//            else {
//                Intent(this, MainActivity::class.java)
//            }
            startActivity(Intent(this, MainActivity::class.java))
//            startActivity( Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun hideBottomUIMenu() {
        val decorView = window.decorView
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT in 12..18) { // lower api
            decorView.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
        }
    }
}