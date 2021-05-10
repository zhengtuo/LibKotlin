package com.drelovey.realize.ui.lib.viewModel

import androidx.databinding.Bindable

import com.drelovey.common.utils.launch
import com.drelovey.common.data.error.ErrorMapper
import com.drelovey.realize.ui.lib.model.CoroutinesModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoroutinesVM @Inject constructor(private val mModel: CoroutinesModel) : BaseModel() {

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

    override var errorMapper = ErrorMapper()

}