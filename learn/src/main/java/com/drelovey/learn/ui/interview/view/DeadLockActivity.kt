package com.drelovey.learn.ui.interview.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.drelovey.common.base.activity.BaseActivity
import com.drelovey.common.base.viewmodel.EmptyViewModel
import com.drelovey.learn.R
import com.drelovey.learn.data.model.Account
import com.drelovey.learn.databinding.ActivityDeadlockBinding
import com.drelovey.provider.router.RouterPath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RouterPath.PATH_INTERVIEW_DEADLOCK)
@AndroidEntryPoint
class DeadLockActivity :
    BaseActivity<ActivityDeadlockBinding, EmptyViewModel>(R.layout.activity_deadlock) {

    // 加时赛锁
    val tieLock = Any()

    override fun initialization() {
        binding {
            lifecycleOwner = this@DeadLockActivity
        }

        initDeadLock()

        binding.btStatic.setOnClickListener {
            staticStateDeadLock()
        }
    }

    private fun initDeadLock() {

        condition()

        //在JAVA编程中,有3种典型的死锁类型：
        //静态的锁顺序死锁,动态的锁顺序死锁,协作对象之间发生的死锁。

        staticStateDeadLock()
    }

    fun condition() {
        //死锁产生的条件
        //一般来说,要出现死锁问题需要满足以下条件：
        //1.互斥条件：一个资源每次只能被一个线程使用。
        //2.请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
        //3.不剥夺条件：线程已获得的资源,在未使用完之前,不能强行剥夺。
        //4.循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。
    }

    //静态的锁顺序死锁
    private fun staticStateDeadLock() {
        //a和b两个方法都需要获得A锁和B锁。
        //一个线程执行a方法且已经获得了A锁,在等待B锁;另一个线程执行了b方法且已经获得了B锁,在等待A锁。这种状态,就是发生了静态的锁顺序死锁。
        a()
        b()
    }

    //可能发生静态锁顺序死锁的代码
    private val lockA: Any = Any()
    private val lockB: Any = Any()

    private fun a() {
        synchronized(lockA) {
            synchronized(lockB) {
                println("function a")
            }
        }
    }

    private fun b() {
        synchronized(lockB) {
            synchronized(lockA) {
                println("function b")
            }
        }
    }

    //解决静态的锁顺序死锁的方法就是：所有需要多个锁的线程，都要以相同的顺序来获得锁。
    private fun a2() {
        synchronized(lockA) { synchronized(lockB) { println("function a") } }
    }

    private fun b2() {
        synchronized(lockA) { synchronized(lockB) { println("function b") } }
    }

    //动态的锁顺序死锁
    private fun dynamicStateDeadLock() {
        //动态的锁顺序死锁是指两个线程调用同一个方法时,传入的参数颠倒造成的死锁。
        //如下代码,一个线程调用了transferMoney方法并传入参数accountA,accountB;
        //另一个线程调用了transferMoney方法并传入参数accountA,accountB;另一个线程调用了transferMoney方法并传入参数accountB,accountA。
        //此时就可能发生在静态的锁顺序死锁中存在的问题,即：第一个线程获得了accountA锁并等待accountB锁,第二个线程获得了accountB锁并等待accountA锁。
    }

    fun transefMoney(fromAccount: Account, toAccount: Account, amount: Double) {
        synchronized(fromAccount) {
            synchronized(toAccount) {
                if (fromAccount < amount) {
                    throw Exception()
                } else {
                    fromAccount.minus(amount)
                    toAccount.add(amount)
                }
            }
        }
    }

    fun transefMoney2(fromAccount: Account, toAccount: Account, amount: Double) {
        class Helper {
            fun tranfer() {
                if (fromAccount < amount) {
                    throw Exception()
                } else {
                    fromAccount.minus(amount)
                    toAccount.add(amount)
                }
            }
        }

        val fromHashCode = System.identityHashCode(fromAccount)
        val toHashCode = System.identityHashCode(toAccount)

        if (fromHashCode < toHashCode) {
            synchronized(fromAccount) {
                synchronized(toAccount) {
                    Helper().tranfer()
                }
            }
        } else if (fromHashCode > toHashCode) {
            synchronized(toAccount) {
                synchronized(fromAccount) {
                    Helper().tranfer()
                }
            }
        } else {
            // 加时赛锁
            synchronized(tieLock) {
                synchronized(fromAccount) {
                    synchronized(toAccount) {
                        Helper().tranfer()
                    }
                }
            }
        }
    }
}