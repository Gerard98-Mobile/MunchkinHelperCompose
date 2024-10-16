package gerard.example.munchkinhelper.presenter.game_container.fight

import androidx.annotation.IntRange
import androidx.lifecycle.ViewModel
import gerard.example.munchkinhelper.model.Fight
import gerard.example.munchkinhelper.model.FightSide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor() : ViewModel() {

    data class State(
        val initialPower: Int = 0,
        val fight: Fight = Fight()
    ) {
        fun reset() : State = this.copy(fight = Fight(initialPower))
    }

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun init(playerPower: Int) {
        _state.update {
            if (it.initialPower == playerPower) return
            it.copy(
                initialPower = playerPower,
                fight = Fight(playerPower)
            )
        }
    }

    fun update(by: Int, @IntRange(0,1) side: FightSide) {
        _state.update {
            it.copy(
                fight = it.fight.update(by, side)
            )
        }
    }

    fun reset() {
        _state.update {
            it.reset()
        }
    }

}