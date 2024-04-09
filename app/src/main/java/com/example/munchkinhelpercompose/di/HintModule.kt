package com.example.munchkinhelpercompose.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.munchkinhelpercompose.AppStore
import com.example.munchkinhelpercompose.data.HintRepository
import com.example.munchkinhelpercompose.data.StoreHintRepository
import com.example.munchkinhelpercompose.use_case.hint.GetHintsUseCase
import com.example.munchkinhelpercompose.use_case.hint.SaveHintsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HintModule {

    @Provides
    fun provideDataStore(): DataStore<Preferences> = AppStore.dataStore

    @Provides
    fun provideHintRepository(): HintRepository = StoreHintRepository(
        provideDataStore(),
        DispatcherIOModule.provideDispatcher()
    )

    @Provides
    fun provideGetHintsUseCase(): GetHintsUseCase = GetHintsUseCase(provideHintRepository())

    @Provides
    fun provideSaveHintsUseCase(): SaveHintsUseCase = SaveHintsUseCase(provideHintRepository())

}