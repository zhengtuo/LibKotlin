package com.drelovey.learn.ui.learn.view

import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.databinding.ActivityNoBinding
import com.drelovey.learn.R

import com.drelovey.realize.ui.learn.model.NoViewModel
import com.drelovey.weight.ShaderView
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantOverride")
/**
 * shader着色器
 */
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