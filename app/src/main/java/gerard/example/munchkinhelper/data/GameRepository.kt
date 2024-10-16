package gerard.example.munchkinhelper.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import gerard.example.munchkinhelper.model.Game
import gerard.example.munchkinhelper.model.Player
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GameRepository {
    suspend fun get(): Flow<Game?>
    suspend fun update(game: Game)
    suspend fun reset()
    suspend fun create(players: List<String>)
}

class StoreGameRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson,
    private val ioDispatcher: CoroutineDispatcher,
): GameRepository {

    companion object {
        private val GAME_KEY = stringPreferencesKey("game_key")
    }

    override suspend fun get(): Flow<Game?> = withContext(ioDispatcher) {
        return@withContext dataStore.data.map {
            it[GAME_KEY]?.let { data ->
                gson.fromJson(data, Game::class.java)
            }
        }
    }

    override suspend fun update(game: Game) {
        withContext(ioDispatcher) {
            dataStore.edit {
                it[GAME_KEY] = gson.toJson(game)
            }
        }
    }

    override suspend fun reset() {
        withContext(ioDispatcher) {
            dataStore.edit {
                val data = it[GAME_KEY] ?: return@edit
                val currentGame = gson.fromJson(data, Game::class.java)
                it[GAME_KEY] = gson.toJson(currentGame.reset())
            }
        }
    }

    override suspend fun create(players: List<String>) {
        withContext(ioDispatcher) {
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

}