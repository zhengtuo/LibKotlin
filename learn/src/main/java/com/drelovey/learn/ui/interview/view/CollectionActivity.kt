package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.common.databinding.ActivityNoBinding
import com.drelovey.learn.R
import com.drelovey.learn.data.model.MyObjectKotlin
import com.drelovey.provider.router.RouterPath
import dagger.hilt.android.AndroidEntryPoint


@Route(path = RouterPath.PATH_INTERVIEW_COLLECTION)
@AndroidEntryPoint
//Java集合大致可以分为Set、List、Queue和Map四种体系。
class CollectionActivity :
    BaseActivity<ActivityNoBinding, EmptyViewModel>(R.layout.activity_no) {
    //kotlin中Collection只提供读写 MutableCollection提供可变操作
    var arrayList: Collection<String> = ArrayList()
    var mutableArrayList: MutableCollection<String> = ArrayList()

    override fun initialization() {
        binding {
            lifecycleOwner = this@CollectionActivity
        }

        initCollection()
        //Set集合：Set代表无序、不可重复的集合;
        //List集合：List代表有序、重复的集合;
        //Queue集合：Queue代表一种队列集合实现;
        //Map集合：Map代表具有映射的集合;

        //LinkedList是一个简单的数据结构,与ArrayList不同的是,它是基于链表实现的。
        initLinkedList()
    }

    private fun initCollection() {
        //Collection用法
        //添加元素
        mutableArrayList.add("1")
        //删除元素
        mutableArrayList.remove("1")
        //返回个数
        mutableArrayList.size
        //清空合集
        mutableArrayList.clear()
        //Iterator遍历
        intIterator()
        objectIterator()

    }

    private fun intIterator() {
        //Iterator接口经常被称作迭代器，它是Collection接口的父接口。Iterator主要用于遍历集合中的元素
        mutableArrayList.add("1")
        mutableArrayList.add("2")
        val iterator: MutableIterator<String> = mutableArrayList.iterator()
        //判断是否有下一个元素
        while (iterator.hasNext()) {
            //取出该元素
            val data = iterator.next()
            println("CollectionActivity:$data")
        }
    }

    //注意：当使用Iterator对集合元素进行迭代时，把集合元素的值传给了迭代变量(就如同参数传递是值传递，基本数据类型传递的是值，引用类型传递的仅仅是对象的引用变量。)
    //引用变量：什么是变量？变量的实质是一小块内存单元。这一小块内存里存储着变量的值。
    //比如int a = 1;a就是变量的命名，1就是变量的值。而当变量指向一个对象时，这个变量就被称为引用变量
    //比如A a = new A(); a就是引用变量，它指向了一个A对象，也可以说它引用了一个A对象。我们通过操纵这个a来操作A对象。此时，变量a的值为它所引用对象的地址。
    private fun objectIterator() {
        val mutableArrayList: MutableCollection<MyObjectKotlin> = ArrayList()
        for (i in 0..9) {
            mutableArrayList.add(MyObjectKotlin(i))
        }

        println("mutableArrayList$mutableArrayList")
        //集合元素的值传给了迭代变量，仅仅传递了对象引用。保存的仅仅是指向对象内存空间的地址
        val iterator: MutableIterator<MyObjectKotlin> = mutableArrayList.iterator()
        while (iterator.hasNext()) {
            val myObject = iterator.next()
            myObject.num = 99
        }
        println("mutableArrayList$mutableArrayList")
    }

    private fun initLinkedList() {

    }

}