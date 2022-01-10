package com.drelovey.learn.data.model


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

    @Synchronized
    fun getImage(): Image {
        val image = Image()
        taxis?.forEach { t ->
            image.drawMarker(t.getLocation())
        }
        println("object getImage")
        return image
    }


}