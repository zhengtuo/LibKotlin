package com.drelovey.realize.data.error

import com.drelovey.common.data.error.Error

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@Suppress("unused")
class AppError(val code: Int, val description: String) {
    constructor(exception: Exception) : this(
        code = Error.UN_KNOW, description = exception.message
            ?: ""
    )

    companion object {
        const val PARAMETER_ERROR = 500
    }
}