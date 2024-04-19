package com.example.munchkinhelpercompose.presenter.game_container.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.model.Settings
import com.example.munchkinhelpercompose.use_case.game.GetGameUseCase
import com.example.munchkinhelpercompose.use_case.game.PlayPlayerChangeSoundEffectUseCase
import com.example.munchkinhelpercompose.use_case.game.UpdateGamePlayerUseCase
import com.example.munchkinhelpercompose.use_case.settings.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGame: GetGameUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updatePlayer: UpdateGamePlayerUseCase,
    private val playChangeSound: PlayPlayerChangeSoundEffectUseCase
) : ViewModel() {

    data class State(
        val game: Game? = null,
        val selected: Player? = null,
        val visibleDialog: GameDialog? = null,
        val settings: Settings? = null
    ) {
        private val allLeaders = game?.players?.let { players ->
            val max = players.maxOf { it.level }
            players.filter { it.level == max }
        } ?: emptyList()

        fun isLeader(player: Player): Boolean = allLeaders.size == 1 && allLeaders.contains(player)
    }

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val _killed = MutableSharedFlow<Player?>()
    val killed = _killed.asSharedFlow()

    init {
        collectGame()
        collectSettings()
    }

    private fun collectGame() = viewModelScope.launch {
        getGame.invoke().first { game ->
            _state.update {
                it.copy(game = game)
            }
            game != null
        }
    }

    private fun collectSettings() = viewModelScope.launch {
        getSettingsUseCase.invoke().collectLatest { settings ->
            _state.update {
                it.copy(settings = settings)
            }
        }
    }

    fun select(player: Player) {
        _state.value = _state.value.copy(selected = player)
    }

    private fun updateState(action: State.() -> State) {
        _state.value = action(state.value)
    }

    fun showDialog(sheet: GameDialog) = updateState {
        this.copy(visibleDialog = sheet)
    }

    fun hideDialog() = updateState {
        this.copy(visibleDialog = null)
    }

    fun update(
        change: Player.() -> Player
    ) = state.value.let { state ->
        if (state.selected == null || state.game == null) return@let

        viewModelScope.launch {
            val result = updatePlayer.invoke(state.selected, change, state.game) ?: return@launch
            val player = result.players.find { it.name ==  state.selected.name } ?: return@launch

            if (player.deaths > state.selected.deaths) _killed.emit(state.selected)

            playChangeSound.invoke(state.selected, player, state.settings)
            _state.value = state.copy(
                game = result,
                selected = player,
                visibleDialog = if (player.level == 10) GameDialog.Winner(player) else null
            )
        }
    }
}