package com.drelovey.realize.ui.lib.viewModel

import androidx.databinding.Bindable
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.realize.ui.main.model.MainModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConstraintLayoutVM @Inject constructor() : BaseViewModel() {

    @get:Bindable
    var title: String by bindingProperty("ConstraintLayout使用详解")
        private set

}