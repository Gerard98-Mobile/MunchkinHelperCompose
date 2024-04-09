package com.example.munchkinhelpercompose.presenter.game_container.fight

import androidx.annotation.IntRange
import androidx.lifecycle.ViewModel
import com.example.munchkinhelpercompose.model.Fight
import com.example.munchkinhelpercompose.model.FightSide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor() : ViewModel() {

    data class State(
        val fight: Fight = Fight()
    )

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun init(playerPower: Int) {
        _state.update {
            it.copy(fight = Fight(playerPower))
        }
    }

    fun update(by: Int, @IntRange(0,1) side: FightSide) {
        _state.update {
            it.copy(
                fight = it.fight.update(by, side)
            )
        }
    }

}