package com.drelovey.realize.di.module

import android.app.Application
import com.drelovey.realize.data.constants.CommonConstants
import com.drelovey.realize.data.remote.interceptor.HeadInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/29 16:53
 */
@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideBaseRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return createRetrofit(builder, client, CommonConstants.BASE_URL, gson)
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d("LoggingInterceptor: %s", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHeadInterceptor(): HeadInterceptor {
        return HeadInterceptor()
    }

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheFile = File(application.cacheDir, CommonConstants.PATH_CACHE)
        return Cache(cacheFile, CommonConstants.CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        builder: OkHttpClient.Builder,
        loggingInterceptor: HttpLoggingInterceptor,
        headInterceptor: HeadInterceptor,
        cache: Cache
    ): OkHttpClient {
        return builder
            .connectTimeout(CommonConstants.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CommonConstants.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CommonConstants.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //.retryOnConnectionFailure(true)
            .addInterceptor(headInterceptor)
            .addInterceptor(loggingInterceptor)
            //.cache(cache)
            .build()
    }

    private fun createRetrofit(
        builder: Retrofit.Builder,
        okhttpClient: OkHttpClient,
        url: String,
        gson: Gson
    ): Retrofit {
        return builder
            .client(okhttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}