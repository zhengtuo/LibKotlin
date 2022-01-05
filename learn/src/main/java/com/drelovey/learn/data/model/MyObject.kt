package com.drelovey.learn.data.model


class MyObject {
    var num = 0

    constructor(num: Int) {
        this.num = num
    }

    override fun toString(): String {
        return num.toString()
    }

}