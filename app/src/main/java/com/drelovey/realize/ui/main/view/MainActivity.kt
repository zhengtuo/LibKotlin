package com.drelovey.realize.ui.main.view

import com.common.base.activity.BaseActivity
import com.drelovey.realize.R
import com.drelovey.realize.databinding.ActivityMainBinding
import com.drelovey.realize.ui.main.viewModel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main) {
    override fun initialization() {
        binding {
            lifecycleOwner = this@MainActivity
            vm = mViewModel
        }
    }
}