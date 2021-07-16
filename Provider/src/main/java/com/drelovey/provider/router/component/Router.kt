package com.drelovey.provider.router.component

/**
 * 组件注册 单例
 */
class Router private constructor() {

    private val services = hashMapOf<String, Any>()

    /**
     * object
     * 声明对象
     * 1.用object关键字进行对象声明后,就可以用类名.方法 的方式调用方法（声明一个类,同时声明该类的一个实例）
     * 2.对象声明一样可以继承类和接口
     * 声明伴生对象
     */
    private object RouterHolder {
        val holder = Router()
    }

    companion object {
        val instance = RouterHolder.holder
    }

    @Synchronized
    fun addService(serviceName: String?, serviceImpl: Any?) {
        if (serviceName == null || serviceImpl == null) {
            return
        }
        services[serviceName] = serviceImpl
    }

    @Synchronized
    fun removeService(serviceName: String?) {
        if (serviceName == null) {
            return
        }
        services.remove(serviceName)
    }

    @Synchronized
    fun getService(serviceName:String?):Any? {
        return if (serviceName == null) {
            null
        } else services[serviceName]
    }
}