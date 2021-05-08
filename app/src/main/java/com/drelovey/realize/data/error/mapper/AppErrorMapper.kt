package com.drelovey.realize.data.error.mapper

import com.drelovey.common.data.error.ErrorMapper
import com.drelovey.realize.R
import com.drelovey.realize.data.error.AppError
import javax.inject.Inject

class AppErrorMapper @Inject constructor() : ErrorMapper() {

    override val errorsMap: HashMap<Int, String>
        get() {
            errorsMap[AppError.PARAMETER_ERROR] = getErrorString(R.string.error_parameter)
            return errorsMap
        }
}