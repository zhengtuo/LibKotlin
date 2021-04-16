package com.drelovey.app

import android.app.Application
import android.content.Context
import com.drelovey.common.base.delegate.ApplicationDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class DreloveyApp : Application() {

    /**
     * Application 代理 规避项目中集成了其它第三方类或其它原因，不能集成本类BaseApplication时，
     * 参照本类实现 ApplicationDelegate 即可初始化MVVMFrame框架配置信息。
     */
    lateinit var mApplicationDelegate: ApplicationDelegate

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        mApplicationDelegate = ApplicationDelegate(this)
        mApplicationDelegate.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationDelegate.onCreate()
        context = applicationContext
        initApp()
    }

    companion object {
        lateinit var context: Context
    }


    /**
     * 初始化app相关
     */
    private fun initApp() {

    }
}