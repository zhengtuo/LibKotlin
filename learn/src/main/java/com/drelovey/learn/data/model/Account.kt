package com.drelovey.learn.data.model

//反射测试类
class Account : Comparable<Double> {

    var moneycount: Double = 0.0

    fun minus(amount: Double) {

    }

    fun add(amount: Double) {

    }

    override fun compareTo(other: Double): Int {
        return (moneycount - other).toInt()
    }

}