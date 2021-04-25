package com.common.data.model

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/20 17:07
 */
sealed class Resource<T>(
        var data: T? = null,
        val methodName: String? = null,
        val errorCode: Int? = null
) {
    class Success<T>(data: T?, methodName: String?) : Resource<T>(data, methodName)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Complete<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorCode: Int) : Resource<T>(null, null, errorCode)
}