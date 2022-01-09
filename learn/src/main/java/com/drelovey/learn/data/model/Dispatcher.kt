package com.drelovey.learn.data.model

import android.media.Image


//反射测试类
class Dispatcher {
    private var taxis: Set<Taxi>? = null
    private var availableTaxis: MutableSet<Taxi>? = null

    constructor() {
        taxis = HashSet()
        availableTaxis = HashSet()

    }

    @Synchronized
    fun notifyAvailable(taxi: Taxi) {
        availableTaxis?.add(taxi)
    }


}