package com.drelovey.learn.data.model


@Suppress("ConvertSecondaryConstructorToPrimary", "RedundantVisibilityModifier")
public class MyObject {
    var num = 0
    var id = 1

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