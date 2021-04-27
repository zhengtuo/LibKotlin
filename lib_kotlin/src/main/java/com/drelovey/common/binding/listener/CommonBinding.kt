package com.drelovey.common.binding.listener

import android.view.View
import com.drelovey.common.utils.LibUtils.getActivityFromView

object CommonBinding {
    //点击返回键
    @JvmField
    var backCommand: BindingClickT<View> = object : BindingClickT<View> {
        override fun click(t: View) {
            val activity = getActivityFromView(t)
            activity?.onBackPressed()
        }
    }

    //关闭activity
    @JvmField
    var finishCommand: BindingClickT<View> = object : BindingClickT<View> {
        override fun click(t: View) {
            val activity = getActivityFromView(t)
            activity?.finish()
        }
    }
}