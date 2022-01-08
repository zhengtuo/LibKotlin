package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.common.databinding.ActivityNoBinding
import com.drelovey.learn.R
import com.drelovey.learn.data.model.MyObjectKotlin
import com.drelovey.provider.router.RouterPath
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Deprecated
import java.lang.reflect.Method
import kotlin.Exception
import kotlin.Int

@Route(path = RouterPath.PATH_INTERVIEW_REFLECT)
@AndroidEntryPoint
//Java反射机制是在运行状态中,对于任意一个类,都能够知道这个类中的所有属性和方法;对于任意一个对象,都能够调用它的任意一个方法和属性;
//这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
open class ReflectActivity :
    BaseActivity<ActivityNoBinding, EmptyViewModel>(R.layout.activity_no) {

    /**
     * Java反射机制的功能
     * 1.在运行时判断任意一个对象所属的类。
     * 2.在运行时构造任意一个类的对象。
     * 3.在运行时判断任意一个类所具有的成员变量和方法。
     * 4.在运行时调用任意一个对象的方法。
     * 5.生成动态代理。
     */
    override fun initialization() {
        binding {
            lifecycleOwner = this@ReflectActivity
        }
        //通过Java反射查看类信息
        seekReflectInfo()
        //通过Java反射生成并操作对象
        createReflectBean()

    }

    private fun seekReflectInfo() {
        initReflect()
        useReflect()
        getClassInfo()
    }

    private fun createReflectBean() {
        createInstance()
        callMethod()
        visitField()
    }

    //kotlin中的Class与Java不同，kotlin中有一个自己的Class叫做KClass
    private fun initReflect() {
        //通过Java反射查看类信息
        //获取Class对象
        //每个类被加载之后,系统就会为该类生成一个对应的Class对象。通过该Class对象就可以访问到JVM中的这个类。
        //在Java程序中获得Class对象通常有如下三种方式：
        //1.使用Class类的forName(String clazzName)静态方法。该方法需要传入字符串参数,该字符串参数的值是某个类的全限定名(必须添加完整包名)。
        val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")
        println(class1)
        //2.调用某个类的class属性来获取该类对应的Class对象。
        val class2 = MyObjectKotlin::class.java
        println(class2)
        //3.调用某个对象的getClass()方法。该方法是java.lang.Object类中的一个方法。
        val myObject = MyObjectKotlin(1)
        val class3: Class<*> = myObject.javaClass
        println(class3)
        println("ReflectActivity:" + (class1 == class2))
        println("ReflectActivity:" + (class2 == class3))
        println("ReflectActivity:" + (class1 == class3))
    }

    //获取class对象的属性、方法、构造函数等
    private fun useReflect() {
        val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")
        val class2 = MyObjectKotlin::class

        //获取class对象的成员变量
        val allFields = class1.declaredFields              //获取class对象的所有属性
        val publicFields = class1.fields                   //获取class对象的public属性
        val numField = class1.getDeclaredField("id")      //获取class指定属性
        val desField = class1.getField("id")               //获取class指定的public属性

        println(allFields + publicFields + numField + desField)

        //获取class对象的方法
        val methods = class1.declaredMethods                   //获取class对象的声明方法
        val allMethods = class1.methods                        //获取class对象的所有public方法 包括父类的方法
        val method = class1.getMethod("getName")                 //返回次Class对象对应类的、带指定形参列表的public方法
        val declaredMethod = class1.getDeclaredMethod("getName") //返回次Class对象对应类的、带指定形参列表的方法

        println(methods + allMethods + method + declaredMethod)

        //获取class对象的构造函数
        val allConstructors = class1.declaredConstructors        //获取class对象的所有声明构造函数
        val publicConstructors = class1.constructors             //获取class对象public构造函数
        val constructor = class1.getDeclaredConstructor(Int::class.java)        //获取指定声明构造函数
        val publicConstructor = class1.getConstructor(Int::class.java)          //获取指定声明的public构造函数

        println(allConstructors + publicConstructors + constructor + publicConstructor)

        //其他方法
        val annotations = class1.annotations                              //获取class对象的所有注解
        val annotation = class1.getAnnotation(Deprecated::class.java)     //获取class对象指定注解
        val genericSuperclass = class1.genericSuperclass                  //获取class对象的直接超类的 Type
        val interfaceTypes = class1.genericInterfaces                     //获取class对象的所有接口的type集合

        println(annotations + annotation)
        println(genericSuperclass)
        println(interfaceTypes)

    }

    //获取class对象的信息
    private fun getClassInfo() {
        val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")
        //获取class对象的信息
        val isPrimitive = class1.isPrimitive                             //判断是否是基础类型
        val isArray = class1.isArray                                     //判断是否是集合类
        val isAnnotation = class1.isAnnotation                           //判断是否是注解类
        val isInterface = class1.isInterface                             //判断是否是接口类
        val isEnum = class1.isEnum                                       //判断是否是枚举类
        val isAnonymousClass = class1.isAnonymousClass                   //判断是否是匿名内部类
        val isAnnotationPresent = class1.isAnnotationPresent(Deprecated::class.java)  //判断是否被某个注解类修饰
        val className = class1.name                                      //获取class名字 包含包名路径
        val aPackage = class1.`package`                                  //获取class的包信息
        val simpleName = class1.simpleName                               //获取class类名
        val modifiers = class1.modifiers                                 //获取class访问权限
        val declaredClasses = class1.declaredClasses                     //内部类
        val declaringClass = class1.declaringClass                       //外部类
    }

    //生成类的实例对象
    private fun createInstance() {
        /**
         * 1.使用Class对象的newInstance()方法来创建该Class对象对应类的实例。
         * 这种方式要求该Class对象的对应类有默认构造器，而执行newInstance()方法时实际上是利用默认构造器来创建该类的实例。
         * 2.先使用Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建该Class对象对应类的实例。
         * 通过这种方式可以选择使用指定的构造器来创建实例。
         */

        try {
            val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")
            //第一种方式 Class对象调用newInstance()方法生成;
            val obj2 = class1.newInstance()

            //第二种方式 对象获得对应的Constructor对象,再通过该Constructor对象的newInstance()方法生成
            val constructor = class1.getDeclaredConstructor(Int::class.java)  //获取指定声明构造函数
            val obj3 = constructor.newInstance(1)

            println(obj2)
            println(obj3)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //调用类的方法
    private fun callMethod() {

        try {
            val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")
            // 生成新的对象：用newInstance()方法
            val obj = class1.newInstance()
            //首先需要获得与该方法对应的Method对象
            val method = class1.getDeclaredMethod("setAge", Int::class.javaPrimitiveType)
            //调用指定的函数并传递参数
            method.invoke(obj, 28)
            //当通过Method的invoke()方法来调用对应的方法时,Java会要求程序必须有调用该方法的权限。
            //如果程序确实需要调用某个对象的private方法，则可以先调用Method对象的如下方法。setAccessible(boolean flag):将Method对象的acessible设置为指定的布尔值。
            //值为true,指示该Method在使用时应该取消Java语言的访问权限检查;值为false,则知识该Method在使用时要实施Java语言的访问权限检查。
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //访问成员变量值
    private fun visitField() {
        //1.通过Class对象的getFields()方法或者getField()方法获得指定方法,返回Field数组或对象。
        //2.Field提供了两组方法来读取或设置成员变量的值：getXXX(Object obj)获取obj对象的该成员变量的值。此处的XXX对应8种基本类型。
        //如果该成员变量setXXX(Object obj,XXX val);将obj对象的该成员变量设置成val值。

        try {
            val class1 = Class.forName("com.drelovey.learn.data.model.MyObjectJava")

            //生成新的对象:用newInstance()方法
            val obj = class1.newInstance()
            //获取id成员变量
            val field = class1.getField("id")
            //将obj对象的id的值设置为10
            field.setInt(obj, 10)
            //获取obj对象的id的值
            field.getInt(obj)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}