package com.drelovey.learn.data.model

import android.graphics.Point

class Taxi {
    private var location: Point? = null
    private var destination: Point? = null
    private var dispatcher: Dispatcher? = null

    constructor(dispatcher: Dispatcher){
        this.dispatcher = dispatcher
    }

    @Synchronized
    fun getLocation(): Point {
        return location ?: Point()
    }

    @Synchronized
    fun setLocation(location: Point) {
        this.location = location
        if (location == destination) {
            dispatcher?.notifyAvailable(this)
        }
        println("object setLocation")
    }
}