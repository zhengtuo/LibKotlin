package com.drelovey.practice.ui.lib.viewModel

import androidx.databinding.Bindable
import com.drelovey.common.base.viewmodel.BaseViewModel

import com.drelovey.common.data.error.ErrorMapper
import com.drelovey.practice.ui.lib.model.CoroutinesModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoroutinesVM @Inject constructor(private val mModel: CoroutinesModel) : BaseViewModel() {

    @get:Bindable
    var title: String by bindingProperty("Coroutines")
        private set

    override var errorMapper = ErrorMapper()

}