package com.drelovey.realize.data.remote

import com.common.data.model.Resource
import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.data.DataGenerator
import com.drelovey.realize.data.error.Error.Companion.DEFAULT_NO_DATA
import com.drelovey.realize.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.drelovey.realize.data.error.Error.Companion.UN_KNOW
import com.drelovey.realize.data.model.CloudClassroomEntity
import com.drelovey.realize.data.remote.service.ApiService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfNotNull
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Author: Drelovey
 * @CreateDate: 2020/1/20 18:22
 */
class RemoteRepository @Inject constructor(private val dataGenerator: DataGenerator) {

    suspend fun getBannerList(type: String): Resource<Any> {
        return processCallByBase(
            {
                dataGenerator.getRetrofitService(ApiService::class.java).getBannerList(type)
            },
            "getBannerList"
        )
    }

    private suspend fun processCallByBase(
        responseCall: suspend () -> ApiResponse<CloudClassroomEntity>,
        methodName: String
    ): Resource<Any> {
        var result: Resource<Any> = Resource.DataError(errorCode = UN_KNOW)
        if (!LibUtils.checkNet()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }
        val response = responseCall.invoke()
        response.suspendOnSuccess {
            result = Resource.DataError(errorCode = DEFAULT_NO_DATA)
            data.whatIfNotNull {
                it.data.whatIfNotNull {
                    result = Resource.Success(data = data!!.data as Any, methodName = methodName)
                }
            }
        }
        response.suspendOnError {
            result = Resource.DataError(errorCode = raw.code)
        }
        return result
    }
}