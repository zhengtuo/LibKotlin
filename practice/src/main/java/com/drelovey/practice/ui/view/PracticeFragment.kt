package com.drelovey.practice.ui.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.app.base.fragment.BaseFragment
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.practice.R
import com.drelovey.practice.databinding.FragmentPracticeBinding
import com.drelovey.provider.router.RouterPath

@Route(path = RouterPath.PATH_PRACTICE)
class PracticeFragment: BaseFragment<FragmentPracticeBinding, EmptyViewModel>(R.layout.fragment_practice){

    override fun initialization() {
        binding {
            lifecycleOwner = this@PracticeFragment
        }
    }

}