package com.drelovey.practice.ui.lib.viewModel

import androidx.databinding.Bindable
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawableDslVM @Inject constructor() : BaseViewModel() {

    @get:Bindable
    var title: String by bindingProperty("DrawableDsl")
        private set

}