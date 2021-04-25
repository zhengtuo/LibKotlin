package com.drelovey.realize.manager.errors

import com.drelovey.realize.data.error.Error


interface ErrorFactory {
    fun getError(errorCode: Int): Error
}