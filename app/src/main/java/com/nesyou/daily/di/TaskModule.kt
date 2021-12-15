package com.nesyou.daily.di

import com.nesyou.daily.core.Database
import com.nesyou.daily.features.task.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TaskModule {

    @Singleton
    @Provides
    fun provideYourDao(db: Database): TaskDao = db.taskDao()

}