package com.drelovey.common.base.model

import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.common.data.error.ErrorMapper
import com.drelovey.common.manager.errors.ErrorManager

open class BaseViewModel : BaseViewModel() {

    open var errorMapper = ErrorMapper()

    val errorManager: ErrorManager
        get() = ErrorManager(
            errorMapper
        )
}