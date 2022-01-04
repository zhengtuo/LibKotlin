package com.drelovey.learn.ui.learn.view

import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.learn.R
import com.drelovey.learn.databinding.ActivityWebviewBinding

class WebViewActivity : BaseActivity<ActivityWebviewBinding, EmptyViewModel>(R.layout.activity_webview) {

    override fun initialization() {
        //webview设置
        binding {
            lifecycleOwner = this@WebViewActivity
        }

        initWebViewSetting()
    }

    fun initWebViewSetting() {

        val setting = binding.webview.settings
        //设置可以支持缩放
        setting.setSupportZoom(true)
        //设置出现缩放工具
        setting.builtInZoomControls = false
    }

}