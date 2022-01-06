package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.common.databinding.ActivityNoBinding
import com.drelovey.learn.R
import com.drelovey.learn.data.model.MyObject
import com.drelovey.provider.router.RouterPath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RouterPath.PATH_INTERVIEW_REFLECT)
@AndroidEntryPoint
//Java反射机制是在运行状态中,对于任意一个类,都能够知道这个类中的所有属性和方法;对于任意一个对象,都能够调用它的任意一个方法和属性;
//这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
class ReflectActivity :
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

        initReflect()
        useReflect()
    }

    //kotlin中的Class与Java不同，kotlin中有一个自己的Class叫做KClass
    private fun initReflect() {
        //通过Java反射查看类信息
        //获取Class对象
        //每个类被加载之后,系统就会为该类生成一个对应的Class对象。通过该Class对象就可以访问到JVM中的这个类。
        //在Java程序中获得Class对象通常有如下三种方式：
        //1.使用Class类的forName(String clazzName)静态方法。该方法需要传入字符串参数,该字符串参数的值是某个类的全限定名(必须添加完整包名)。
        val class1 = Class.forName("com.drelovey.learn.data.model.MyObject")
        println(class1)
        //2.调用某个类的class属性来获取该类对应的Class对象。
        val class2 = MyObject::class.java
        println(class2)
        //3.调用某个对象的getClass()方法。该方法是java.lang.Object类中的一个方法。
        val myObject = MyObject(1)
        val class3: Class<*> = myObject.javaClass
        println(class3)
        println("ReflectActivity:" + (class1 == class2))
        println("ReflectActivity:" + (class2 == class3))
        println("ReflectActivity:" + (class1 == class3))
    }

    //获取class对象的属性、方法、构造函数等
    private fun useReflect() {
        val class1 = Class.forName("com.drelovey.learn.data.model.MyObject")
        //获取class对象的成员变量

        val allFields = class1.declaredFields          //获取class对象的所有属性

        val publicFields = class1.fields               //获取class对象的public属性

        val numField = class1.getDeclaredField("num")  //获取class指定属性
        //获取class指定的public属性
        //val desField = class1.getField("id")

        println(allFields + publicFields + numField)
        //获取class对象的方法
        val methods = class1.declaredMethods          //获取class对象的声明方法
        val allMethods = class1.methods               //获取class对象的所有public方法 包括父类的方法
        val method = class1.getMethod("getId")        //返回次Class对象对应类的、带指定形参列表的public方法
        val declaredMethod = class1.getDeclaredMethod("getId") //返回次Class对象对应类的、带指定形参列表的方法


        //获取class对象的构造函数
        class1.getDeclaredConstructor()
    }
}