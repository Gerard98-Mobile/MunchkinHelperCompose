package com.example.munchkinhelpercompose

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object AppStore {
    private const val STORE_NAME = "mh_store"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)
    lateinit var dataStore: DataStore<Preferences>

    fun init(applicationContext: Context) {
        dataStore = applicationContext.dataStore
    }

}