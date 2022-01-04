package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.learn.R
import com.drelovey.learn.databinding.ActivityInterviewBinding
import com.drelovey.provider.router.RouterPath
import com.drelovey.realize.ui.learn.model.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RouterPath.PATH_INTERVIEW)
@AndroidEntryPoint
class InterviewActivity :
    BaseActivity<ActivityInterviewBinding, NoViewModel>(R.layout.activity_interview) {
    override fun initialization() {
        binding {
            lifecycleOwner = this@InterviewActivity
        }
    }

}