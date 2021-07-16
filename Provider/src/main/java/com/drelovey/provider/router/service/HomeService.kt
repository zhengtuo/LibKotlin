package com.drelovey.provider.router.service

import androidx.databinding.ViewDataBinding
import com.drelovey.common.app.base.fragment.BaseFragment
import com.drelovey.common.base.viewmodel.BaseViewModel

interface HomeService<T : ViewDataBinding,VM : BaseViewModel> {

    fun  getHomeFragment():BaseFragment<T,VM>
}