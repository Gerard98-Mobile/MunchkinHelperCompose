package gerard.example.munchkinhelper.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import gerard.example.munchkinhelper.data.SettingsRepository
import gerard.example.munchkinhelper.data.StoreSettingsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object SettingsModule {

    @Provides
    fun provideSettingsRepository(
        dataStore: DataStore<Preferences>,
        gson: Gson,
        dispatcher: CoroutineDispatcher
    ): SettingsRepository = StoreSettingsRepository(dataStore, gson, dispatcher)

}