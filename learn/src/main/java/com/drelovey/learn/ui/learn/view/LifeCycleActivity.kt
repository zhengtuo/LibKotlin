package com.drelovey.learn.ui.learn.view

import android.content.res.Configuration
import android.os.Bundle
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.databinding.ActivityNoBinding
import com.drelovey.learn.R
import com.drelovey.realize.ui.learn.model.NoViewModel

@Suppress("RedundantOverride")
/**
 * Activity的生命周期
 */
class LifeCycleActivity : BaseActivity<ActivityNoBinding, NoViewModel>(R.layout.activity_no) {
    override fun initialization() {

    }
    //当Activity第一次创建时会被调用。这是生命周期的第一个方法。
    //在这个方法中，可以做一些初始化工作，比如调用setContentView去加载界面布局资源，初始化Activity所需的数据。
    //当然也可借助onCreate()方法中的Bundle对象来回复异常情况下Activity结束时的状态。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //表示Activity正在重新启动。一般情况下，当当前Activity从不可见重新变为可见状态时，onRestart就会被调用。
    //这种情况一般是用户行为导致的，比如用户按Home键切换到桌面或打开了另一个新的Activity,接着用户又回到了这个Activity。
    override fun onRestart() {
        super.onRestart()
    }

    //表示Activity正在被启动，即将开始，这时Activity已经出现了，但是还没有出现在前台，无法与用户交互。
    //这个时候可以理解为Activity已经显示出来，但是我们还看不到。
    override fun onStart() {
        super.onStart()
    }

    //表示Activity已经可见了，并且出现在前台并开始活动。
    //需要和onStart()对比，onStart的时候Activity还在后台，onResume的时候Activity才显示到前台。
    override fun onResume() {
        super.onResume()
    }

    //表示Activity正在停止，扔可见，正常情况下，紧接着onStop就会被调用。
    //在特殊情况下，如果这个时候快速地回到当前Activity,那么onResume就会被调用（极端情况）。
    //onPause中不能进行耗时操作，会影响到新Activity的显示。因为onPause必须执行完，新的Activity的onResume才会执行。
    override fun onPause() {
        super.onPause()
    }

    //表示Activity即将停止，不可见，位于后台。
    //可以做稍微重量级的回收工作，同样不能太耗时。
    override fun onStop() {
        super.onStop()
    }

    //表示Activity即将销毁，这是Activity生命周期的最后一个回调，可以做一些回收工作和最终的资源回收。
    override fun onDestroy() {
        super.onDestroy()
    }

    //在Activity由于异常情况下终止时，系统会调用onSaveInstanceState来保存当前Activity的状态。
    //这个方法的调用是在onStop之前，它和onPause没有既定的时序关系，该方法只在Activity被异常终止的情况下调用。
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    //当异常终止的Activity被重建以后，系统会调用onRestoreInstanceState，
    //并且把Activity销毁时OnSaveInstanceState方法所保存的Bundle对象参数同时传递给onRestoreInstanceState和onCreate方法。
    //因此，可以通过onRestoreInstanceState方法来恢复Activity的状态，该方法的调用时机是在onStart之后。
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    //可以通过在AndroidManifest文件的Activity中指定如下属性：
    //android:configChanges = “orientation|screenSize”
    //来避免横竖屏切换时，Activity的销毁和重建，而是回调了下面的方法：
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}