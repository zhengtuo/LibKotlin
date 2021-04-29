package com.drelovey.realize.lib.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

class MyContinuationInterceptor: ContinuationInterceptor{
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

