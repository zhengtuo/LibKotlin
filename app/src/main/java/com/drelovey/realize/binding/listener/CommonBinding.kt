package com.drelovey.realize.binding.listener

import com.drelovey.common.binding.listener.BindingClickT
import com.drelovey.common.binding.listener.BindingCommand
import com.drelovey.realize.arouter.Router.getInstance

/**
 * @Author: Drelovey
 * @CreateDate: 2020/10/9 15:51
 */
object CommonBinding {

    @JvmField
    var jumpClick: BindingClickT<String> = object : BindingClickT<String> {
        override fun click(t: String) {
            getInstance().build(t).navigation()
            //Timber.d(t)
        }
    }

    @JvmField
    var jumpCommand = BindingCommand(jumpClick)

}