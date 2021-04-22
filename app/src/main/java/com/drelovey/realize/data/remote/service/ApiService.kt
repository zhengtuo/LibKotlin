package com.drelovey.realize.data.remote.service

import com.drelovey.realize.data.model.BaseEntity
import com.drelovey.realize.data.model.HomeBanner
import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface ApiService {

    @GET("api/Index/banners")
    suspend fun getBannerList(@Query("banner_type") type: String): BaseEntity<HomeBanner>

}