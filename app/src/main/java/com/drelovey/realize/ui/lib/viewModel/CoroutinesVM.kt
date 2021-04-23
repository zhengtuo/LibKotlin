package com.drelovey.realize.ui.lib.viewModel

import androidx.databinding.Bindable
import com.common.data.model.Resource
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.common.utils.launch
import com.drelovey.realize.ui.lib.model.CoroutinesModel
import com.drelovey.realize.ui.main.model.MainModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoroutinesVM @Inject constructor(private val mModel: CoroutinesModel) : BaseViewModel() {

    @get:Bindable
    var title: String by bindingProperty("Coroutines")
        private set


    fun getBannerList(type: String) {
        launch({
            dataLiveData.postValue(mModel.getHomeBaner(type))
        }, {
            Timber.d("CoroutinesVM %s", it.message)
        })

    }

}