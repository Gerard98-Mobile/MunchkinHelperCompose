package com.example.munchkinhelpercompose.di

import android.content.Context
import com.example.munchkinhelpercompose.SoundManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object SoundPoolModule {

    @Provides
    fun provideSoundManager(@ApplicationContext applicationContext: Context): SoundManager =
        SoundManager(applicationContext)

}