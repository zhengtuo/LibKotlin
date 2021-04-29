package com.drelovey.realize.ui.lib.view

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.activity.BaseActivity
import com.common.data.model.Resource
import com.drelovey.common.utils.LibUtils
import com.drelovey.realize.R
import com.drelovey.realize.arouter.RouterPath
import com.drelovey.realize.data.error.Error
import com.drelovey.realize.data.model.User
import com.drelovey.realize.data.remote.service.GitHubService
import com.drelovey.realize.databinding.ActivityCoroutinesBinding
import com.drelovey.realize.lib.coroutines.MyContinuationInterceptor
import com.drelovey.realize.ui.lib.viewModel.CoroutinesVM
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import javax.security.auth.callback.Callback
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

//kotlinx.coroutines 协程使用范例  协程是一种非抢占式或者说协作式的计算机程序并发调度的实现
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

    /**
     * 启动模式  DEFAULT 立即执行协程体 ATOMIC 立即执行协程体,但在开始运行之前无法取消 UNDISPATCHED 立即在当前线程执行协程体，直到第一个suspend调用 LAZY只有在需要的情况下运行
     * public enum class CoroutineStart {
    DEFAULT,
    LAZY,
    @ExperimentalCoroutinesApi
    ATOMIC,
    @ExperimentalCoroutinesApi
    UNDISPATCHED;
    }
     */


    val gitHubServiceApi by lazy {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GitHubService::class.java)
    }

    override fun initialization() {
        binding {
            lifecycleOwner = this@CoroutinesActivity
        }
        //mingTao()
        //crackKotlin1()

        crackKotlin3()
    }


    private fun mingTao() {
        mViewModel.getBannerList("10")
    }

    //入门
    //join 要求等待协程执行完
    fun crackKotlin1() {
        //一个常规例子
//        gitHubServiceApi.geUseree("bennyhuo").enqueue(object:Callback<User>,
//            retrofit2.Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                //handler.post { response.body()?.let(::showUser) ?: showError(NullPointerException()) }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                //handler.post { showError(t) }
//            }
//
//        })

        //CallAdapter 的方式
        GlobalScope.launch(Dispatchers.Main) {
            try {
                mViewModel.dataLiveData.postValue(
                    Resource.Success(
                        data = gitHubServiceApi.getUser("bennyhuo").await(), methodName = "un know"
                    )
                )
            } catch (e: Exception) {
                //showError(e)
            }
        }
        //注意以下不是正确的代码，仅供大家理解协程使用
//        GlobalScope.launch { Dispatchers.Main } {
//            gitHubServiceApi.getUser("bennyhuo").await(Continuation<User> {
//                override fun resume(value:User) {
//                    showUser(value)
//                }
//                override fun resumeWithException(exception:Throwable) {
//                    showError(exception)
//                }
//            })
//        }

        //而在await当中,大致就是：
        //注意以下并不是真实的实现,仅供大家理解协程使用
//        fun await(continuation:Continuation<User>):Any {
//            ...// 切到非 UI线程中执行，等待结果返回
//            try {
//                val user = ...
//                handler.post{ continuaion.resume(user) }
//            } catch (e:Exception) {
//                handler.post{ continuation.resumeWithException(e) }
//            }
//        }
    }

    //协程启动篇
    fun crackKotlin2() {
        GlobalScope.launch {
            //default()
            //lazy()
            //atomic()
            undispatched()
        }
    }

    //协程调度篇 CoroutineContext协程上下文
    fun crackKotlin3() {
        //协程上下文 调度器本质上就是一个协程上下文的实现

        //我们可以通过指定上下文为协程添加一些特性，一个很好的例子就是为协程添加名称，方便调试：
        GlobalScope.launch(CoroutineName("Hello")) {
            //...
        }

        //如果有多个上下文需要添加，直接用 + 就可以了： Dispatchers.Main 是调度器的一个实现，不用担心，我们很快就会认识它了。
        GlobalScope.launch(Dispatchers.Main + CoroutineName("Hello")) {
            //...
        }

        //协程拦截器 拦截器也是一个上下文的实现方向，拦截器可以左右你的协程的执行，同时为了保证它的功能的正确性，协程上下文集合永远将它放在最后面
        GlobalScope.launch {
            interceptor()
        }

        GlobalScope.launch {
            combinedContext()
        }

        //绑定到任意线程的调度器
        //调度器的目的就是切线程
        GlobalScope.launch {
            scheduler()
        }

    }

    /**
     * DEFAULT
     * 四个启动模式当中我们最常用的其实是 DEFAULT 和 LAZY。
     * DEFAULT 是饿汉式启动，launch 调用后，会立即进入待调度状态，一旦调度器 OK 就可以开始执行。
     */
    suspend fun default() {
        Timber.d("default 1")
        val job = GlobalScope.launch {
            Timber.d("default 2")
        }
        Timber.d("default 3")
        job.join()
        Timber.d("default 4")
    }

    /**
     * LAZY
     * LAZY是懒汉式启动，launch 后并不会有任何调度行为，协程体也自然不会进入执行状态，直到我们需要它执行的时候。
     * 这其实就有点儿费解了，什么叫我们需要它执行的时候呢？就是需要它的运行结果的时候， launch 调用后会返回一个 Job 实例
     * 对于这种情况，我们可以：
     * 调用 Job.start，主动触发协程的调度执行
     * 调用 Job.join，隐式的触发协程的调度执行
     */
    fun lazy() {
        Timber.d("lazy 1")
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            Timber.d("lazy 2")
        }
        Timber.d("lazy 3")
        job.start()
        Timber.d("lazy 4")
    }

    /**
     * ATOMIC
     * ATOMIC 只有涉及 cancel 的时候才有意义
     * 对于 ATOMIC 模式，我们已经讨论过它一定会被启动，实际上在遇到第一个挂起点之前，它的执行是不会停止的
     */
    suspend fun atomic() {
//        Timber.d("atomic 1")
//        val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//            Timber.d("atomic 2")
//        }
//        job.cancel()
//        Timber.d("atomic 3")

        Timber.d("atomic 1")
        val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            Timber.d("atomic 2")
            delay(1000)
            Timber.d("atomic 3")
        }
        job.cancel()
        Timber.d("atomic 4")
        job.join()
    }

    /**
     * UNDISPATCHED
     * UNDISPATCHED 不经过任何调度器即开始执行协程体
     */
    suspend fun undispatched() {
        Timber.d("undispatched 1")
        val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            Timber.d("undispatched 2")
            delay(1000)
            Timber.d("undispatched 3")
        }
        Timber.d("undispatched 4")
        job.join()
        Timber.d("undispatched 5")
    }

    //在 JVM 上 delay 实际上是在一个 ScheduledExcecutor 里面添加了一个延时任务，因此会发生线程切换
    //本身是协程上下文的子类，同时实现了拦截器的接口， dispatch 方法会在拦截器的方法 interceptContinuation 中调用，进而实现协程的调度
    suspend fun interceptor() {
        GlobalScope.launch(MyContinuationInterceptor()) {
            Timber.d("crackKotlin3 1")
            val job = async {
                Timber.d("crackKotlin3 2")
                delay(1000)
                Timber.d("crackKotlin3 3")
                "Hello"
            }
            Timber.d("crackKotlin3 4")
            val result = job.await()
            Timber.d("crackKotlin3 5 $result")
        }.join()
        Timber.d("crackKotlin3 6")
    }

    //CombinedContext 类型，表示有很多具体的上下文实现的集合，我们如果想要找到某一个特别的上下文实现，就需要用对应的 Key 来查找
    fun combinedContext() {
        GlobalScope.launch {
            println(coroutineContext[Job]) // "coroutine#1":StandaloneCoroutine{Active}@1ff62014
        }
        println(coroutineContext)
    }

    suspend fun scheduler() {
        val myDispatcher = Executors.newSingleThreadExecutor { r ->
            Thread(r, "MyThread")
        }.asCoroutineDispatcher()
        GlobalScope.launch(myDispatcher) {
            log(1)
        }.join()
        log(2)
    }

    suspend fun getUserCoroutine() = suspendCoroutine<User> {
//        continuation ->
//        getUser {
//            continuation.resume(it)
//        }
    }

    //typealias Callback = (User) -> Unit

    fun getUser(callback: Callback){

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

    val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

    val now = {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")


}