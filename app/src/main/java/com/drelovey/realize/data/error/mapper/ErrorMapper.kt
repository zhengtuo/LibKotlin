package com.drelovey.realize.data.error.mapper

import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.R
import com.drelovey.realize.data.error.Error
import com.task.data.error.mapper.ErrorMapperInterface
import javax.inject.Inject

class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return LibUtils.getStringById(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.error_no_internet)),
            Pair(Error.UN_KNOW, getErrorString(R.string.error_un_know))
        )
}