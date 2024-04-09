package com.example.munchkinhelpercompose.store

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.munchkinhelpercompose.AppStore
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import com.google.gson.Gson
import kotlinx.coroutines.flow.map

class GameStore {
    companion object {
        private val GAME_KEY = stringPreferencesKey("game_key")
    }

    private val gson = Gson()

    val get = AppStore.dataStore.data.map {
        it[GAME_KEY]?.let {
            gson.fromJson(it, Game::class.java)
        }
    }

    suspend fun update(game: Game) = AppStore.dataStore.edit {
        it[GAME_KEY] = gson.toJson(game)
    }

    suspend fun create(playerNames: List<String>) = AppStore.dataStore.edit {
        val game = Game(
            players = buildSet {
                playerNames.forEach {
                    add(Player(it))
                }
            }
        )
        it[GAME_KEY] = gson.toJson(game)
    }

}
