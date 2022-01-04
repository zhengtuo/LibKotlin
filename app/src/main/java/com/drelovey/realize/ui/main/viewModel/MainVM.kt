package com.drelovey.realize.ui.main.viewModel

import androidx.databinding.ObservableField
import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.realize.ui.main.model.MainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val mModel: MainModel) : BaseViewModel() {
    var mSelect = ObservableField<Int>()

    init {
        mSelect.set(0)
    }

    fun setSelect(select: Int) {
        if (mSelect.get() != select) {
            mSelect.set(select)
        }
    }


}