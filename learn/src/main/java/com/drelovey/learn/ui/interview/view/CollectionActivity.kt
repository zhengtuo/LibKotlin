package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.learn.R
import com.drelovey.learn.databinding.ActivityInterviewBinding
import com.drelovey.provider.router.RouterPath
import com.drelovey.realize.ui.learn.model.NoViewModel
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RouterPath.PATH_INTERVIEW_COLLECTION)
@AndroidEntryPoint
class CollectionActivity :
    BaseActivity<ActivityInterviewBinding, NoViewModel>(R.layout.activity_interview) {
    //kotlin中Collection只提供读写 MutableCollection提供可变操作
    var arrayList: Collection<String> = ArrayList()
    var mutableArrayList: MutableCollection<String> = ArrayList()

    override fun initialization() {
        binding {
            lifecycleOwner = this@CollectionActivity
        }
    }

    override fun initDelegate() {
        //Collection用法
        //添加元素
        mutableArrayList.add("1")
        //删除元素
        mutableArrayList.remove("1")
        //返回个数
        mutableArrayList.size
        //清空合集
        mutableArrayList.clear()
        //Iterator遍历  Iterator接口经常被称作迭代器，它是Collection接口的父接口。Iterator主要用于遍历集合中的元素
        mutableArrayList.add("1")
        mutableArrayList.add("2")
        val iterator: MutableIterator<String> = mutableArrayList.iterator()
        //判断是否有下一个元素
        while (iterator.hasNext()) {
            val data = iterator.next()
            println("CollectionActivity:$data")
        }

    }


}