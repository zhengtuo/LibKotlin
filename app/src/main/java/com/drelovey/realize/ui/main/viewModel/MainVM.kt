package com.drelovey.realize.ui.main.viewModel

import android.view.View
import androidx.databinding.Bindable
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.realize.ui.main.model.MainModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val mModel: MainModel) : BaseViewModel() {

    @get:Bindable
    var title: String by bindingProperty("drelovey")
        private set

    fun avatarClick() {
        //Some code here
    }

}