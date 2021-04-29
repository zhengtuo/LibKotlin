package com.drelovey.realize.lib.coroutines

import timber.log.Timber
import kotlin.coroutines.Continuation

class MyContinuation<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        Timber.d("<MyContinuation> $result" )
        continuation.resumeWith(result)
    }
}