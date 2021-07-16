package com.drelovey.realize.ui.main.viewModel

import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.realize.ui.main.model.MainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val mModel: MainModel) : BaseViewModel()