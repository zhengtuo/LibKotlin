package com.drelovey.realize.app.base

import com.drelovey.common.base.viewmodel.BaseViewModel
import com.drelovey.realize.data.error.mapper.ErrorMapper
import com.drelovey.realize.manager.errors.ErrorManager

open class BaseViewModel:BaseViewModel() {
    val errorManager: ErrorManager
        get() = ErrorManager(
            ErrorMapper()
        )
}