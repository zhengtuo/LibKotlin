package com.drelovey.common.binding.listener

class BindingCommand<T> {
    private var click: BindingClick? = null
    private var clickT: BindingClickT<T>? = null
    private var clickRT: BindingClickRT<Boolean>? = null

    private var data: T? = null

    //点击
    constructor(click: BindingClick) {
        this.click = click
    }

    //带泛型
    constructor(clickT: BindingClickT<T>) {
        this.clickT = clickT
    }

    fun setClickT(clickT: BindingClickT<T>) {
        this.clickT = clickT
    }

    fun setData(data: T): BindingCommand<T> {
        this.data = data
        return this
    }

    //带控制
    constructor(click: BindingClick, clickRT: BindingClickRT<Boolean>) {
        this.click = click
        this.clickRT = clickRT
    }

    //带泛型 带控制
    constructor(clickT: BindingClickT<T>, clickRT: BindingClickRT<Boolean>) {
        this.clickT = clickT
        this.clickRT = clickRT
    }

    fun click() {
        if (canClick()) {
            if (data != null) {
                click(data!!)
            } else {
                click?.click()
            }

        }
    }

    fun click(parameter: T) {
        if (clickT != null && canClick()) {
            clickT?.click(parameter)
        }
    }

    private fun canClick(): Boolean {
        return if (clickRT == null) {
            true
        } else clickRT?.click() ?: true
    }

}