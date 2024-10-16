package gerard.example.munchkinhelper.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import gerard.example.munchkinhelper.data.GameRepository
import gerard.example.munchkinhelper.data.StoreGameRepository
import gerard.example.munchkinhelper.use_case.game.UpdateGamePlayerUseCase
import gerard.example.munchkinhelper.use_case.game.UpdateGameUseCase
import gerard.example.munchkinhelper.use_case.game.UpdatePlayerUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object GameModule {

    @Provides
    fun provideGameRepository(
        dataStore: DataStore<Preferences>,
        gson: Gson,
        dispatcher: CoroutineDispatcher
    ): GameRepository = StoreGameRepository(dataStore, gson, dispatcher)

    @Provides
    fun provideUpdatePlayerUseCase(): UpdatePlayerUseCase = UpdatePlayerUseCase()

    @Provides
    fun provideUpdateGameUseCase(
        dataStore: DataStore<Preferences>,
        gson: Gson,
        dispatcher: CoroutineDispatcher
    ): UpdateGameUseCase = UpdateGameUseCase(provideGameRepository(dataStore, gson, dispatcher))

    @Provides
    fun provideUpdateGamePlayerUseCase(
        dataStore: DataStore<Preferences>,
        gson: Gson,
        dispatcher: CoroutineDispatcher
    ): UpdateGamePlayerUseCase = UpdateGamePlayerUseCase(provideUpdateGameUseCase(dataStore, gson, dispatcher), provideUpdatePlayerUseCase())

}