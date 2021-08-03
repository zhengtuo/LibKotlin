package com.drelovey.realize.data.model

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/22 16:31
 */
//@JsonClass moshi必备
//@JsonClass(generateAdapter = true)
data class CloudClassroomEntity<T> (

    val code: Int = 0,
    var data: T? = null,
    val status: Boolean = false

)