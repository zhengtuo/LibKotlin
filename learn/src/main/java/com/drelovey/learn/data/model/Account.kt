package com.drelovey.learn.data.model

//反射测试类
class Account : Comparable<Double> {

    var moneycount: Double = 100000.0

    fun minus(amount: Double) {
        moneycount -= amount
    }

    fun add(amount: Double) {
        moneycount += amount
    }

    override fun compareTo(other: Double): Int {
        return (moneycount - other).toInt()
    }

}