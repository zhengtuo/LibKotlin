package com.drelovey.realize.data.model

/**
 * data 关键字 data class就是一个类中只包含一些数据字段
 * 注意事项 1.主构造函数必须要有一个参数2.主构造函数中的所有参数必须被标记为val或者var
 * 3.数据类不能有以下修饰符：abstract,inner,open,sealed
 * 4.data class只能实现接口（Kotlin1.1以前的规则）,现在也可以继承其它类
 */
data class User(
    val id: String, val name: String, val url: String
)
