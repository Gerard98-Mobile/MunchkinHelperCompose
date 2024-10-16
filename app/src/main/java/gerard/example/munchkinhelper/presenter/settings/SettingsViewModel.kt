package gerard.example.munchkinhelper.presenter.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gerard.example.munchkinhelper.model.Settings
import gerard.example.munchkinhelper.use_case.game.ResetGameUseCase
import gerard.example.munchkinhelper.use_case.settings.GetSettingsUseCase
import gerard.example.munchkinhelper.use_case.settings.UpdateSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettings: GetSettingsUseCase,
    private val updateSettings: UpdateSettingsUseCase,
    private val resetGame: ResetGameUseCase,
) : ViewModel() {

    data class State(
        val settings: Settings = Settings(),
        val bottomSheet: SettingsBottomSheets? = null
    )

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        initState()
    }

    private fun initState() = viewModelScope.launch {
        getSettings.invoke().collectLatest { settings ->
            settings?.let {
                _state.update {
                    it.copy(settings = settings)
                }
            }
        }
    }

    fun changeVisibleBottomSheet(sheet: SettingsBottomSheets?) = _state.update {
        it.copy(
            bottomSheet = sheet
        )
    }

    fun updateSettings(func: (Settings) -> Settings) = viewModelScope.launch {
        val newSettings = func.invoke(state.value.settings).let(func)
        updateSettings.invoke(newSettings)
    }

    fun resetGame() = viewModelScope.launch {
        resetGame.invoke()
    }

}