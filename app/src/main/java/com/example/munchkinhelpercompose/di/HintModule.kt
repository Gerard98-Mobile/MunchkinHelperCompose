package com.example.munchkinhelpercompose.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.munchkinhelpercompose.data.HintRepository
import com.example.munchkinhelpercompose.data.StoreHintRepository
import com.example.munchkinhelpercompose.use_case.hint.GetHintsUseCase
import com.example.munchkinhelpercompose.use_case.hint.SaveHintsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
class HintModule {

    @Provides
    fun provideHintRepository(
        dataStore: DataStore<Preferences>,
        dispatcher: CoroutineDispatcher
    ): HintRepository = StoreHintRepository(dataStore, dispatcher)

    @Provides
    fun provideGetHintsUseCase(
        dataStore: DataStore<Preferences>,
        dispatcher: CoroutineDispatcher
    ): GetHintsUseCase = GetHintsUseCase(provideHintRepository(dataStore, dispatcher))

    @Provides
    fun provideSaveHintsUseCase(
        dataStore: DataStore<Preferences>,
        dispatcher: CoroutineDispatcher
    ): SaveHintsUseCase = SaveHintsUseCase(provideHintRepository(dataStore, dispatcher))

}