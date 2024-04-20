package com.example.munchkinhelpercompose.presenter.manage_game.edit_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.presenter.manage_game.edit_game.bottom_sheet.EditGameBottomSheet
import com.example.munchkinhelpercompose.use_case.game.GetGameUseCase
import com.example.munchkinhelpercompose.use_case.game.UpdateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditGameViewModel @Inject constructor(
    private val getGame: GetGameUseCase,
    private val updateGame: UpdateGameUseCase,
) : ViewModel() {

    data class State(
        val game: Game? = null,
        val visibleBottomSheets: EditGameBottomSheet? = null
    )

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

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

    fun removePlayer(player: Player) = _state.update {
        it.copy(
            game = it.game?.copy(
                players = it.game.players.minus(player)
            )
        )
    }

    fun changeVisibleBottomSheet(bottomSheet: EditGameBottomSheet?) = _state.update {
        it.copy(
            visibleBottomSheets = bottomSheet
        )
    }

    fun saveChanges() = viewModelScope.launch {
        state.value.game?.let {
            updateGame.invoke(it)
        }
    }

    fun changePlayerName(oldPlayer: Player, newName: String) = _state.update {
        val newListOfPlayers = it.game?.players?.map { player ->
            if (player.name == oldPlayer.name) player.copy(name = newName)
            else player
        }

        it.copy(
            game = it.game?.copy(
                players = newListOfPlayers?.toSet() ?: it.game.players
            )
        )
    }

}