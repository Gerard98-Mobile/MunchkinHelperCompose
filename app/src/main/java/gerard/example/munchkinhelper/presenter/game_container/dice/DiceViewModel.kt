package gerard.example.munchkinhelper.presenter.game_container.dice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiceViewModel @Inject constructor() : ViewModel() {

    data class DiceThrow(
        val rollCounter: Int,
        val result: Int = (1..6).random(),
    )

    private var throwCounter = 0

    private val _dice = MutableStateFlow<DiceThrow?>(null)
    val dice = _dice.asStateFlow()

    fun throwDice() = viewModelScope.launch {
        _dice.value = DiceThrow(++throwCounter)
    }
}