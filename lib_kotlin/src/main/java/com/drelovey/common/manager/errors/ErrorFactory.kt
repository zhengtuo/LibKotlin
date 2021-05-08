package com.drelovey.common.manager.errors

import com.drelovey.common.data.error.Error


interface ErrorFactory {
    fun getError(errorCode: Int): Error
}