package com.drelovey.realize.ui.learn.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.realize.R
import com.drelovey.provider.router.RouterPath
import com.drelovey.realize.databinding.ActivityNoBinding
import com.drelovey.realize.ui.learn.model.NoViewModel
import com.drelovey.realize.weight.ShaderView
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantOverride")
/**
 * shader着色器
 */
@Route(path = RouterPath.PATH_SHADER)
@AndroidEntryPoint
class ShaderActivity :
    BaseActivity<ActivityNoBinding, NoViewModel>(R.layout.activity_no) {

    override fun initialization() {

        val view = ShaderView(applicationContext)
        view.text = "2021/7/6决心搞定一切"
        view.setTextColor(resources.getColor(R.color.color_3))
        setContentView(view)
    }
}