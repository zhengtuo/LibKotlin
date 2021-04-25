package com.drelovey.realize.manager.errors

import com.drelovey.realize.data.error.mapper.ErrorMapper
import javax.inject.Inject
import com.drelovey.realize.data.error.Error

/**
 * Created by Drelovey on 4/25/2021
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}