package gerard.example.munchkinhelper.presenter.manage_game.new_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gerard.example.munchkinhelper.use_case.game.CreateGameUseCase
import gerard.example.munchkinhelper.use_case.hint.GetHintsUseCase
import gerard.example.munchkinhelper.use_case.hint.SaveHintsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGameViewModel @Inject constructor(
    private val getHints: GetHintsUseCase,
    private val saveHints: SaveHintsUseCase,
    private val createGame: CreateGameUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val DEFAULT_SAFE_LEVELS = 3
    }

    data class UiState(
        val players: List<String> = emptyList(),
        val hints: Set<String> = emptySet(),
        val safeLevels: Int = DEFAULT_SAFE_LEVELS
    )

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private val _snackbar = MutableSharedFlow<String>()
    val snackbar = _snackbar.asSharedFlow()

    init {
        collectHints()
    }

    fun addPlayer(
        playerName: String,
    ) {
        _state.update {
            it.copy(
                players = it.players.toMutableList().apply { add(0, playerName) },
            )
        }
    }

    fun removePlayer(name: String) = _state.update {
        it.copy(
            players = it.players.toMutableList().apply {
                remove(name)
            }
        )
    }

    private fun collectHints() = viewModelScope.launch(dispatcher) {
        getHints.invoke().first().let { data ->
            _state.update {
                it.copy(hints = data)
            }
        }
    }

    fun createGame() = viewModelScope.launch(dispatcher) {
        createGame.invoke(state.value.players, state.value.safeLevels)
        saveHints.invoke(state.value.players.toList())
    }

    fun showSnackbar(message: String) = viewModelScope.launch {
        _snackbar.emit(message)
    }

    fun increaseSafeLevels() {
        _state.update {
            val safeLevels = it.safeLevels + 1
            it.copy(safeLevels = if (safeLevels >= 10) 0 else safeLevels)
        }
    }

}