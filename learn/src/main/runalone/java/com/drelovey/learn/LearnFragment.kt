package com.drelovey.learn

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.app.base.fragment.BaseFragment
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.learn.R
import com.drelovey.learn.databinding.FragmentHomeBinding
import com.drelovey.provider.router.RouterPath

@Route(path = RouterPath.PATH_LEARN)
class LearnFragment: BaseFragment<FragmentHomeBinding, EmptyViewModel>(R.layout.fragment_home){

    override fun initialization() {

    }

}