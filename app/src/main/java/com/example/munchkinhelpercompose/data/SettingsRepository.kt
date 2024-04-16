package com.example.munchkinhelpercompose.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.munchkinhelpercompose.model.Settings
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SettingsRepository {
    suspend fun get(): Flow<Settings?>
    suspend fun update(settings: Settings)

}

class StoreSettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson,
    private val ioDispatcher: CoroutineDispatcher
) : SettingsRepository {

    companion object {
        private val SETTINGS_KEY = stringPreferencesKey("settings_key")
    }

    override suspend fun get(): Flow<Settings?> = withContext(ioDispatcher) {
        return@withContext dataStore.data.map {
            it[SETTINGS_KEY]?.let { data ->
                gson.fromJson(data, Settings::class.java)
            }
        }
    }

    override suspend fun update(settings: Settings) {
        withContext(ioDispatcher) {
            dataStore.edit {
                it[SETTINGS_KEY] = gson.toJson(settings)
            }
        }
    }
}