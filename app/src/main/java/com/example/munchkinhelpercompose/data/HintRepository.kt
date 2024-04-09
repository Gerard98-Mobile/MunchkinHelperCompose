package com.example.munchkinhelpercompose.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.munchkinhelpercompose.AppStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface HintRepository {
    fun get(): Flow<Set<String>>
    suspend fun save(hints: List<String>)
}

class StoreHintRepository(
    private val dataStore: DataStore<Preferences> = AppStore.dataStore
) : HintRepository {

    companion object {
        private const val HINTS_STORE_SIZE = 50
        private val HINTS_KEY = stringSetPreferencesKey("hints_key")
    }

    override fun get(): Flow<Set<String>> {
        return dataStore.data.map {
            it[HINTS_KEY] ?: emptySet()
        }
    }

    override suspend fun save(hints: List<String>) {
        dataStore.edit {
            val data = it[HINTS_KEY]?.toMutableList() ?: mutableListOf()
            data.addAll(0, hints)
            val set = data.take(HINTS_STORE_SIZE).toSet()
            it[HINTS_KEY] = set
        }
    }
}