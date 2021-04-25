package com.drelovey.realize.data.remote.service

import com.drelovey.realize.data.model.CloudClassroomEntity
import com.drelovey.realize.data.model.DataBean
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface ApiService {

    @GET("api/Index/banners2")
    suspend fun getBannerList(@Query("banner_type") type: String): ApiResponse<CloudClassroomEntity<List<DataBean>>>

    @GET("api/Index/banners2")
    suspend fun getBannerList2(@Query("banner_type") type: String): CloudClassroomEntity<DataBean>

}