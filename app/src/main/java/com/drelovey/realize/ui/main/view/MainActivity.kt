package com.drelovey.realize.ui.main.view

import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.provider.router.RouterPath
import com.drelovey.realize.R
import com.drelovey.realize.databinding.ActivityMainBinding
import com.drelovey.realize.ui.main.viewModel.MainVM
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main) {

    private val mStack = Stack<Fragment>()
    override fun initialization() {
        binding {
            lifecycleOwner = this@MainActivity
            vm = mViewModel
        }

        initFragment()
    }

    private fun initFragment() {
        val bt = supportFragmentManager.beginTransaction()

        val mLearnFragment =
            ARouter.getInstance().build(RouterPath.PATH_LEARN).navigation()
        if (mLearnFragment != null) {
            bt.add(R.id.container, mLearnFragment as Fragment)
        }

        bt.commit()
    }

    fun setSelect(view: View) {
        val select = view.tag as Int
        mViewModel.setSelect(select)
    }

}