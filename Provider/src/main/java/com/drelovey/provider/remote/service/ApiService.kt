package com.drelovey.provider.remote.service

import com.drelovey.provider.data.model.BaseEntity
import com.drelovey.provider.data.model.DataBean
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface ApiService {

    @GET("api/Index/banners")
    suspend fun getBannerList(@Query("banner_type") type: String): ApiResponse<BaseEntity<List<DataBean>>>

}