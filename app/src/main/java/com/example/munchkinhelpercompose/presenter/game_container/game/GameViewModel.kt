package com.example.munchkinhelpercompose.presenter.game_container.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.use_case.game.GetGameUseCase
import com.example.munchkinhelpercompose.use_case.game.UpdateGamePlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGame: GetGameUseCase,
    private val updatePlayer: UpdateGamePlayerUseCase
) : ViewModel() {

    data class State(
        val game: Game? = null,
        val selected: Player? = null,
        val isFightDialogVisible: Boolean = false,
        val isDiceDialogVisible: Boolean = false
    ) {
        private val allLeaders = game?.players?.let { players ->
            val max = players.maxOf { it.level }
            players.filter { it.level == max }
        } ?: emptyList()

        fun isLeader(player: Player): Boolean = allLeaders.size == 1 && allLeaders.contains(player)
    }

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val _winner = MutableSharedFlow<Player?>()
    val winner = _winner.asSharedFlow()

    init {
        collectGame()
    }

    private fun collectGame() = viewModelScope.launch {
        getGame.invoke().first().let { game ->
            _state.update {
                it.copy(game = game)
            }
        }
    }

    fun select(player: Player) {
        _state.value = _state.value.copy(selected = player)
    }

    private fun updateState(action: State.() -> State) {
        _state.value = action(state.value)
    }

    fun isFightDialogVisible(value: Boolean) = updateState {
        this.copy(isFightDialogVisible = value)
    }

    fun isDiceDialogVisible(value: Boolean) = updateState {
        this.copy(isDiceDialogVisible = value)
    }

    fun update(
        change: Player.() -> Player
    ) = state.value.let { state ->
        if (state.selected == null || state.game == null) return@let

        viewModelScope.launch {
            val result = updatePlayer.invoke(state.selected, change, state.game) ?: return@launch

            val player = result.players.find { it.name ==  state.selected.name }
            _state.value = state.copy(
                game = result,
                selected = player,
            )

            if (player?.level == 10) {
                _winner.emit(player)
            }
        }
    }

}