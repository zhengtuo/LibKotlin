package com.drelovey.realize.binding.listener

interface Callback<T> {
    fun onSuccess(value: T)

    fun onError(t: Throwable)
}