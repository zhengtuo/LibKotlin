package com.drelovey.realize.binding.listener

import android.view.View
import com.drelovey.common.binding.listener.BindingGenericity
import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.arouter.Router.getInstance

/**
 * @Author: Drelovey
 * @CreateDate: 2020/10/9 15:51
 */
object CommonBinding {
    @JvmField
    val jumpCommand: BindingGenericity<String> = BindingGenericity {
        getInstance().build(it).navigation()
    }


    @JvmStatic
    fun dismissDialog(view: View) {
        val activity = LibUtils.getActivityFromView(view)
        activity?.onBackPressed()
    }
}