package com.example.munchkinhelpercompose.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.AppStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GameRepository {
    suspend fun get(): Flow<Game?>
    suspend fun update(game: Game)
    suspend fun create(players: List<String>)
}

class StoreGameRepository(
    private val dataStore: DataStore<Preferences> = AppStore.dataStore,
    private val gson: Gson = Gson()
): GameRepository {

    companion object {
        private val GAME_KEY = stringPreferencesKey("game_key")
    }

    override suspend fun get(): Flow<Game?> {
        return dataStore.data.map {
            it[GAME_KEY]?.let { data ->
                gson.fromJson(data, Game::class.java)
            }
        }
    }

    override suspend fun update(game: Game) {
        dataStore.edit {
            it[GAME_KEY] = gson.toJson(game)
        }
    }

    override suspend fun create(players: List<String>) {
        dataStore.edit {
            val game = Game(
                players = buildSet {
                    players.forEach {
                        add(Player(it))
                    }
                }
            )
            it[GAME_KEY] = gson.toJson(game)
        }
    }

}