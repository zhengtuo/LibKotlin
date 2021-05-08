package com.drelovey.common.manager.errors

import com.drelovey.common.data.error.Error
import com.drelovey.common.data.error.ErrorMapper
import javax.inject.Inject

/**
 * Created by Drelovey on 4/25/2021
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}