package com.drelovey.realize.data.error

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class Error(val code: Int, val description: String) {
    constructor(exception: Exception) : this(
            code = DEFAULT_ERROR, description = exception.message
        ?: ""
    )

    companion object {
        const val NO_INTERNET_CONNECTION = -1
        const val DEFAULT_NO_DATA = -2
        const val DEFAULT_ERROR = -3
        const val CAN_NOT_REGISTER = 2042
        const val NO_DATA = -4
        const val UN_KNOW = -5
    }
}