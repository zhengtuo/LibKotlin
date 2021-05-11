package com.drelovey.common.base.delegate

import android.app.Application
import android.content.Context
import android.graphics.Color
import com.drelovey.common.utils.LibUtils
import com.drelovey.common.BuildConfig
import com.drelovey.common.app.base.lifecycle.ApplicationLifecycle
import com.jeremyliao.liveeventbus.LiveEventBus
import com.skydoves.whatif.whatIf
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/28 12:04
 */
open class ApplicationDelegate(application: Application) : ApplicationLifecycle {

    var mApplication: Application = application


    override fun attachBaseContext(base: Context) {
        //Debug模式下开启日志
        if (BuildConfig.DEBUG) {                             // 这两行必须写在init之前，否则这些配置在init过程中将无效
            Timber.plant(DebugTree())
        }
    }

    override fun onCreate() {

        LibUtils.init(mApplication)
        // 在主进程中初始化相关数据
        if (LibUtils.isMainProcess(mApplication)) {
            if (BuildConfig.DEBUG) {                         // 这两行必须写在init之前，否则这些配置在init过程中将无效
                // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            }
            initLiveEventBus()
        }

    }

    override fun onTerminate() {

    }

    override fun onLowMemory() {

    }

    override fun onTrimMemory(level: Int) {

    }

    fun initLiveEventBus() {
        LiveEventBus.config()
            .setContext(mApplication)
            .lifecycleObserverAlwaysActive(true)
            .whatIf(BuildConfig.DEBUG, { enableLogger(true) }, { enableLogger(false) })

    }

}