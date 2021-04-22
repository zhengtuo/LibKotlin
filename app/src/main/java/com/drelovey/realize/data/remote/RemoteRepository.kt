package com.drelovey.realize.data.remote

import com.common.data.model.Resource
import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.data.DataGenerator
import com.drelovey.realize.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.drelovey.realize.data.error.Error.Companion.UN_KNOW
import com.drelovey.realize.data.model.BaseEntity
import com.drelovey.realize.data.remote.service.ApiService
import javax.inject.Inject

/**
 *
 * @Author: Drelovey
 * @CreateDate: 2020/1/20 18:22
 */
class RemoteRepository @Inject constructor(private val dataGenerator: DataGenerator) {

    suspend fun getBannerList(type: String): Resource<Any> {
        return processCallByBase(
            { dataGenerator.getRetrofitService(ApiService::class.java).getBannerList(type) },
            "getBannerList"
        )
    }

    private suspend fun processCallByBase(
        responseCall: suspend () -> BaseEntity<*>,
        methodName: String
    ): Resource<Any> {
        if (!LibUtils.checkNet()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }
        return try {
            val response = responseCall.invoke()
            if (response.status) {
                response.data?.let {
                    return Resource.Success(data = response.data as Any, methodName = methodName)
                }
                Resource.Success(data = methodName, methodName = methodName)
            } else {
                Resource.DataError(errorCode = response.code)
            }
        } catch (e: Exception) {
            return Resource.DataError(errorCode = UN_KNOW)
        }
    }
}