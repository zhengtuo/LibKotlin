@file:Suppress("unused")

package com.drelovey.provider.remote

import com.drelovey.common.data.model.Resource
import com.drelovey.common.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.drelovey.common.data.error.Error.Companion.UN_KNOW
import com.drelovey.common.utils.LibUtils
import com.drelovey.common.data.DataGenerator
import com.drelovey.provider.data.model.BaseEntity
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject

/**
 *
 * @Author: Drelovey
 * @CreateDate: 2020/1/20 18:22
 */
class RemoteRepository @Inject constructor(private val dataGenerator: DataGenerator) {

    private suspend fun processCallByApi(
            responseCall: suspend () -> ApiResponse<BaseEntity<*>>,
            methodName: String
    ): Resource<Any> {
        var result: Resource<Any> = Resource.DataError(errorCode = UN_KNOW, null)
        if (!LibUtils.checkNet()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION, null)
        }
        try {
            val response = responseCall.invoke()

            response.suspendOnSuccess {
                data.whatIfNotNull {
                    result = Resource.Success(data = it.data as Any, methodName = methodName)
                }
            }
            response.suspendOnError {
                result = Resource.DataError(errorCode = raw.code, errorCase = raw.message)
            }
        } catch (e: Exception) {
            result = Resource.DataError(errorCode = UN_KNOW, errorCase = e.message)
        }
        return result
    }
}