package com.drelovey.realize.data.model

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/22 16:31
 */
class BaseEntity<T> {


    val code: Int = 0
    var data: T? = null
    val status: Boolean = false

}