package com.example.munchkinhelpercompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherIOModule {
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}