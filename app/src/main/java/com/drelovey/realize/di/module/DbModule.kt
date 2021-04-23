package com.drelovey.realize.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/29 16:53
 */
@Module
@InstallIn(SingletonComponent::class)
class DbModule {

//    @Provides
//    @Singleton
//    fun provideStaffBeanDao(appDatabase: AppDatabase): StaffBeanDao {
//        return appDatabase.staffBeanDao()
//    }
}