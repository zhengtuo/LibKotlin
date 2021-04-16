package com.common.base.activity

import android.app.Activity
import java.lang.ref.WeakReference

class BaseActivityDelegate(mActivity: Activity) {
    private var weakReference: WeakReference<Activity>? = null

    init {
        if (weakReference == null) {
            weakReference = WeakReference(mActivity)
        }
    }


    fun onDestroy() {
        weakReference?.clear()
    }
}