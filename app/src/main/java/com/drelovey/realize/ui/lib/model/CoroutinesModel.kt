package com.drelovey.realize.ui.lib.model

import com.common.data.model.Resource
import com.drelovey.realize.data.remote.RemoteRepository
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class CoroutinesModel @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getHomeBaner(type:String): Resource<Any> {
        return remoteRepository.getBannerList(type)
    }
}