package com.drelovey.provider.binding.listener

interface Callback<T> {
    fun onSuccess(value: T)

    fun onError(t: Throwable)
}