package com.example.munchkinhelpercompose.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.use_case.game.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGame: GetGameUseCase
) : ViewModel() {

    data class State(
        val previousGame: Game? = null
    )

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        collectPreviousGame()
    }

    private fun collectPreviousGame() = viewModelScope.launch {
        getGame.invoke().collectLatest { game ->
            _state.update {
                it.copy(previousGame = game)
            }
        }
    }

}