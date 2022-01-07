package com.drelovey.learn.data.model

//反射测试类
@Suppress("ConvertSecondaryConstructorToPrimary", "RedundantVisibilityModifier")
class MyObjectKotlin {
    var num = 0

    companion object {
        const val id = 1
    }

    constructor(num: Int) {
        this.num = num
    }

    override fun toString(): String {
        return num.toString()
    }

    fun getId(): String {
        return id.toString()
    }

}