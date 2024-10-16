package gerard.example.munchkinhelper.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import gerard.example.munchkinhelper.data.HintRepository
import gerard.example.munchkinhelper.data.StoreHintRepository
import gerard.example.munchkinhelper.use_case.hint.GetHintsUseCase
import gerard.example.munchkinhelper.use_case.hint.SaveHintsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object HintModule {

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