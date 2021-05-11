package com.drelovey.realize.ui.lib.view

import androidx.constraintlayout.widget.ConstraintProperties
import androidx.constraintlayout.widget.ConstraintSet
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.activity.BaseActivity
import com.drelovey.realize.arouter.RouterPath
import com.drelovey.realize.R
import com.drelovey.realize.databinding.ActivityConstraintlayoutBinding
import com.drelovey.realize.ui.lib.viewModel.ConstraintLayoutVM
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

//ConstraintLayout使用详解
@Route(path = RouterPath.PATH_CONSTRAINT)
@AndroidEntryPoint
class ConstraintLayoutActivity :
    BaseActivity<ActivityConstraintlayoutBinding, ConstraintLayoutVM>(R.layout.activity_constraintlayout) {

    override fun initialization() {
        binding {
            lifecycleOwner = this@ConstraintLayoutActivity
            vm = mViewModel
        }

        //ConstraintProperties（流式API） 2.0 提供了ConstraintProperties可以使用流式 API修改属性
        val properties = ConstraintProperties(findViewById(R.id.iv1))
        properties.translationZ(32f).margin(ConstraintSet.START, 43).apply()

        // MotionLayout 它提供了一个丰富的动画系统来协调多个视图之间的动画效果
    }
}