package com.drelovey.realize.binding.listener

import com.alibaba.android.arouter.launcher.ARouter
import com.drelovey.common.binding.listener.BindingClickT
import com.drelovey.common.binding.listener.BindingCommand


/**
 * @Author: Drelovey
 * @CreateDate: 2020/10/9 15:51
 */
object CommonBinding {

    @JvmField
    var jumpClick: BindingClickT<String> = object : BindingClickT<String> {
        override fun click(t: String) {
            ARouter.getInstance().build(t).navigation()
            //Timber.d(t)
        }
    }

    @JvmField
    var jumpCommand = BindingCommand(jumpClick)

}