package com.drelovey.realize.data.remote.service

import com.drelovey.realize.data.model.CloudClassroomEntity
import com.drelovey.realize.data.model.DataBean
import com.drelovey.realize.data.model.User
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface GitHubService {

//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): Call<User>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Deferred<User>

    @GET("users/{login}")
    suspend fun getUser2(@Path("login") login: String): User
}