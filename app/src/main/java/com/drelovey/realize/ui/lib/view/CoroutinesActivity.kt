package com.drelovey.realize.ui.lib.view

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.activity.BaseActivity
import com.common.data.model.Resource
import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.R
import com.drelovey.realize.arouter.RouterPath
import com.drelovey.realize.data.error.Error
import com.drelovey.realize.databinding.ActivityCoroutinesBinding
import com.drelovey.realize.ui.lib.viewModel.CoroutinesVM
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber

//kotlinx.coroutines 协程使用范例
@Route(path = RouterPath.PATH_COROUTINES)
@AndroidEntryPoint
class CoroutinesActivity :
    BaseActivity<ActivityCoroutinesBinding, CoroutinesVM>(R.layout.activity_coroutines) {

    /**
     * Dispatchers指定线程
     * Dispatchers.Main：Android 中的主线程
     * Dispatchers.IO：针对磁盘和网络 IO 进行了优化，适合 IO 密集型的任务，比如：读写文件，操作数据库以及网络请求
     * Dispatchers.Default：适合 CPU 密集型的任务，比如计算
     * Dispatchers.Unconfined：不推荐使用
     */

    override fun initialization() {
        binding {
            lifecycleOwner = this@CoroutinesActivity
        }

        //basic()
        //tutorial()

        mingTao()

    }

    //This section covers basic coroutine concepts. 协程的基本概念
    fun basic() {
        //first
        //delay()
        //runBlocking()
        //runBlocking2()
        //job()
        coroutine()
        //refactoring()
        //light_weight()
        //sleep()
    }

    fun tutorial() {

        val deferred = (1..100).map { n ->
            GlobalScope.async {
                n
            }
        }

        runBlocking {
            val sum = deferred.sumOf { it.await().toLong() }
            println("Sum: $sum")
        }

        val deferred2 = (1..15).map { n ->
            GlobalScope.async {
                delay(1000)
                n
            }
        }

        runBlocking {
            val sum = deferred2.sumOf { it.await().toLong() }
            println("Sum: $sum")
        }

        val deferred3 = (1..55).map { n ->
            GlobalScope.async {
                workload(n)
            }
        }

        runBlocking {
            val sum = deferred3.sumOf { it.await().toLong() }
            println("Sum: $sum")
        }

    }

    /**
     * Kotlin coroutines are extremely inexpensive in comparison to threads. Each time when we want to start a new computation asynchronously, we can create a new coroutine.
     * To start a new coroutine we use one of the main "coroutine builders": launch, async, or runBlocking. Different libraries can define additional coroutine builders.
     *
     * async starts a new coroutine and returns a Deferred object. Deferred represents a concept known by other names such as Future or Promise: it stores a computation,
     * but it defers the moment you get the final result; it promises the result sometime in the future.
     *
     * The main difference between async and launch is that launch is used for starting a computation that isn't expected to return a specific result. launch returns Job,
     * which represents the coroutine. It is possible to wait until it completes by calling Job.join().
     *
     * Deferred is a generic type which extends Job.
     * An async call can return a Deferred<Int> or Deferred<CustomType> depending on what the lambda returns (the last expression inside the lambda is the result).
     */
    fun concurrency() {

    }

    /**
     * Essentially, coroutines are light-weight threads. They are launched with launch coroutine builder in a context of some CoroutineScope.
     * Here we are launching a new coroutine in the GlobalScope, meaning that the lifetime of the new coroutine is limited only by the lifetime of the whole application.
     * You can achieve the same result by replacing GlobalScope.launch { ... } with thread { ... }, and delay(...) with Thread.sleep(...). Try it (don't forget to import kotlin.concurrent.thread).
     * If you start by replacing GlobalScope.launch with thread, the compiler produces the following error:
     * Error: Kotlin: Suspend functions are only allowed to be called from a coroutine or another suspend function
     * That is because delay is a special suspending function that does not block a thread, but suspends the coroutine, and it can be only used from a coroutine.
     */
    fun delay() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms) 无阻塞延迟
            Timber.d("delay World!")
        }
        Timber.d("delay Hello,") // main thread continues while coroutine is delayed 协程延迟时，主线程继续
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
        Timber.d("delay kotlinx,")
    }

    /**
     * Bridging blocking and non-blocking worlds
     * The first example mixes non-blocking delay(...) and blocking Thread.sleep(...) in the same code.
     * It is easy to lose track of which one is blocking and which one is not. Let's be explicit about blocking using the runBlocking coroutine builder:
     * The result is the same, but this code uses only non-blocking delay. The main thread invoking runBlocking blocks until the coroutine inside runBlocking completes.
     */
    fun runBlocking() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            Timber.d("runBlocking World!")
        }
        Timber.d("runBlocking Hello")// main thread continues here immediately
        runBlocking {               // but this expression blocks the main thread
            delay(2000L)            // ... while we delay for 2 seconds to keep JVM alive
        }
        Timber.d("runBlocking kotlinx.")
    }

    /**
     * This example can be also rewritten in a more idiomatic way, using runBlocking to wrap the execution of the main function:
     * Here runBlocking<Unit> { ... } works as an adaptor that is used to start the top-level main coroutine.
     * We explicitly specify its Unit return type, because a well-formed main function in Kotlin has to return Unit.
     */
    fun runBlocking2() = runBlocking<Unit> { // start main coroutine
        GlobalScope.launch {// launch a new coroutine in background and continue
            delay(1000L)
            Timber.d("runBlocking2 World!")
        }
        Timber.d("runBlocking2 Hello")// main coroutine continues here immediately
        delay(2000L)                  // delaying for 2 seconds to keep JVM alive
        Timber.d("runBlocking2 kotlinx.")
    }

    fun testMySuspendingFunction() = runBlocking<Unit> {
        // here we can use suspending functions using any assertion style that we like
    }

    /**
     * Waiting for a job
     * Delaying for a time while another coroutine is working is not a good approach.
     * Let's explicitly wait (in a non-blocking way) until the background Job that we have launched is complete:
     */

    fun job() = runBlocking {
        //sampleStart
        val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            Timber.d("job World!")
        }
        Timber.d("job Hello,")
        job.join() // wait until child coroutine completes job完成后才能继续执行
        Timber.d("job kotlinx.")
        //sampleEnd
    }

    /**
     * Scope builder
     * In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using the coroutineScope builder.
     * It creates a coroutine scope and does not complete until all launched children complete.
     * Note that right after the "Task from coroutine scope" message (while waiting for nested launch)
     * "Task from runBlocking" is executed and printed — even though the coroutineScope is not completed yet.
     * runBlocking中不管开启多少个子协程它们都是使用runBlocking所使用的那一条线程来完成任务的
     */
    fun coroutine() = runBlocking { // this: CoroutineScope
        launch {
            delay(2000L)
            Timber.d("coroutine Task from runBlocking")
        }
        coroutineScope { // Creates a coroutine scope
            launch {
                delay(3500L)
                Timber.d("coroutine Task from nested launch")
            }

            //delay(5000L)
            Timber.d("coroutine Task from coroutine scope") // This line will be printed before the nested launch
        }

        Timber.d("coroutine Coroutine scope is over") // This line is not printed until the nested launch completes
    }

    /**
     * Extract function refactoring
     * Let's extract the block of code inside launch { ... } into a separate function.
     * When you perform "Extract function" refactoring on this code, you get a new function with the suspend modifier.
     * This is your first suspending function. Suspending functions can be used inside coroutines just like regular functions,
     * but their additional feature is that they can, in turn, use other suspending functions (like delay in this example) to suspend execution of a coroutine.
     *
     * But what if the extracted function contains a coroutine builder which is invoked on the current scope? In this case,
     * the suspend modifier on the extracted function is not enough. Making doWorld an extension method on CoroutineScope is one of the solutions,
     * but it may not always be applicable as it does not make the API clearer.
     * The idiomatic solution is to have either an explicit CoroutineScope as a field in a class containing the target function or an implicit one when the outer class implements CoroutineScope.
     * As a last resort, CoroutineScope(coroutineContext) can be used,
     * but such an approach is structurally unsafe because you no longer have control on the scope of execution of this method. Only private APIs can use this builder.
     */

    fun refactoring() = runBlocking {
        launch {
            doWorld()
        }
        Timber.d("refactoring Hello,")
    }

    // this is your first suspending function
    suspend fun doWorld() {
        delay(1000L)
        println("refactoring World!")
    }

    /**
     * Coroutines ARE light-weight
     * It launches 100K coroutines and, after 5 seconds, each coroutine prints a dot.
     * Now, try that with threads. What would happen? (Most likely your code will produce some sort of out-of-memory error)
     */
    fun light_weight() = runBlocking {
        repeat(100_000) { // launch a lot of coroutines
            launch {
                delay(5000L)
                Timber.d(".")
            }
        }
    }

    /**
     * Global coroutines are like daemon threads
     * The following code launches a long-running coroutine in GlobalScope that prints "I'm sleeping" twice a second and then returns from the main function after some delay:
     */
    fun sleep() = runBlocking {
        //sampleStart
        GlobalScope.launch {
            repeat(100) { i ->
                Timber.d("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // just quit after delay
        Timber.d("I'm sleeping end")
        //sampleEnd
    }

    /**
     * Our workload() function can be called from a coroutine (or another suspending function),
     * but cannot be called from outside a coroutine. Naturally, delay() and await() that we used above are themselves declared as suspend,
     * and this is why we had to put them inside runBlocking {}, launch {} or async {}.
     */
    suspend fun workload(n: Int): Int {
        delay(1000)
        return n
    }


    /**
     * To get the result of a coroutine, we call await() on the Deferred instance. While waiting for the result, the coroutine that this await is called from, suspends:
     */
    fun async() = runBlocking {
        val deferred: Deferred<Int> = async {
            loadData2()
        }
        println("waiting...")
        println(deferred.await())
    }

    suspend fun loadData2(): Int {
        println("loading...")
        delay(1000L)
        println("loaded!")
        return 42
    }

    /**
     * runBlocking is used as a bridge between regular and suspend functions, between blocking and non-blocking worlds.
     * It works as an adaptor for starting the top-level main coroutine and is intended primarily to be used in main functions and in tests.
     * If there is a list of deferred objects, it is possible to call awaitAll to await the results of all of them:
     */
    fun awaitAll() = runBlocking {
        val deferreds: List<Deferred<Int>> = (1..3).map {
            async {
                delay(1000L * it)
                println("Loading $it")
                it
            }
        }
        val sum = deferreds.awaitAll().sum()
        println("$sum")
    }

    private fun mingTao() {
        mViewModel.getBannerList("10")
    }

    //请求回调
    @ExperimentalCoroutinesApi
    override fun handleData(resource: Resource<*>) {
        when (resource) {
            is Resource.Loading -> {

            }
            is Resource.Complete -> {

            }
            is Resource.Success -> {
                val name = resource.methodName
                val data = resource.data

            }
            is Resource.DataError -> {
                resource.errorCode.whatIfNotNull {
                    val error = mViewModel.errorManager.getError(resource.errorCode!!)
                    if (error.description == LibUtils.getStringById(R.string.error_un_know)) {
                        Toast.makeText(mContext, resource.errorCase ?: "未知错误", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(mContext, error.description, Toast.LENGTH_SHORT).show()
                    }

                }

            }
        }
    }

}