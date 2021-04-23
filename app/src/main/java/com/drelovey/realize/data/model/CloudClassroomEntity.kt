package com.drelovey.realize.data.model

import com.squareup.moshi.JsonClass

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/22 16:31
 */
@JsonClass(generateAdapter = true)
data class CloudClassroomEntity (

    val code: Int = 0,
    var data: List<DataBean>? = null,
    val status: Boolean = false

)