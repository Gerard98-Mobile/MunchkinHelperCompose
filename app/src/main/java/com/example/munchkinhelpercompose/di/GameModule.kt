package com.example.munchkinhelpercompose.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.munchkinhelpercompose.AppStore
import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.data.StoreGameRepository
import com.example.munchkinhelpercompose.use_case.game.UpdateGamePlayerUseCase
import com.example.munchkinhelpercompose.use_case.game.UpdateGameUseCase
import com.example.munchkinhelpercompose.use_case.game.UpdatePlayerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class GameModule {

    @Provides
    fun provideDataStore(): DataStore<Preferences> = AppStore.dataStore

    @Provides
    fun provideGameRepository(): GameRepository = StoreGameRepository(
        provideDataStore(),
        GsonModule.provideGson(),
        DispatcherIOModule.provideDispatcher()
    )

    @Provides
    fun provideUpdatePlayerUseCase(): UpdatePlayerUseCase = UpdatePlayerUseCase()

    @Provides
    fun provideUpdateGameUseCase(): UpdateGameUseCase = UpdateGameUseCase(provideGameRepository())

    @Provides
    fun provideUpdateGamePlayerUseCase(): UpdateGamePlayerUseCase = UpdateGamePlayerUseCase(provideUpdateGameUseCase(), provideUpdatePlayerUseCase())

}