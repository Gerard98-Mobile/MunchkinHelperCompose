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

    data class UiState(
        val players: List<String> = emptyList(),
        val hints: Set<String> = emptySet(),
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
        createGame.invoke(state.value.players)
        saveHints.invoke(state.value.players.toList())
    }

    fun showSnackbar(message: String) = viewModelScope.launch {
        _snackbar.emit(message)
    }

}