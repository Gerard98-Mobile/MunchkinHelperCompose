package com.example.munchkinhelpercompose.presenter.new_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.use_case.game.CreateGameUseCase
import com.example.munchkinhelpercompose.use_case.hint.GetHintsUseCase
import com.example.munchkinhelpercompose.use_case.hint.SaveHintsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
        val error: String? = null
    )

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        collectHints()
    }

    fun addPlayer(
        playerName: String,
    ) {
        when {
            state.value.players.contains(playerName) -> changeErrorState("Player already exists")
            playerName.isEmpty() -> changeErrorState("Player name is empty")
            else -> {
                _state.update {
                    it.copy(
                        players = it.players.toMutableList().apply { add(0, playerName) },
                        error = null
                    )
                }
            }
        }
    }

    private fun collectHints() = viewModelScope.launch(dispatcher) {
        getHints.invoke().collectLatest { data ->
            _state.update {
                it.copy(hints = data)
            }
        }
    }

    fun changeErrorState(error: String?) = _state.update {
        it.copy(error = error)
    }

    fun createGame() = viewModelScope.launch(dispatcher) {
        createGame.invoke(state.value.players)
        saveHints.invoke(state.value.players.toList())
    }

}