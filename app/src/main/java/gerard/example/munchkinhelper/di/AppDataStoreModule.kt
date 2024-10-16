package gerard.example.munchkinhelper.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataStoreModule {

    private const val STORE_NAME = "mh_store"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)
    lateinit var dataStore: DataStore<Preferences>

    @Provides
    fun provideAppDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> =
        if (::dataStore.isInitialized) dataStore
        else applicationContext.dataStore.apply {
            dataStore = this
        }

}