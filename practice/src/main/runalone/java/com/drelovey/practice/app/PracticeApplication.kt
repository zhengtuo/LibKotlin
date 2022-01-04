package com.drelovey.practice.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.drelovey.practice.BuildConfig


class PracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            //开启调试模式
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}