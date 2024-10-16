package gerard.example.munchkinhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gerard.example.munchkinhelper.model.Settings
import gerard.example.munchkinhelper.use_case.settings.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSettings: GetSettingsUseCase
) : ViewModel() {

    data class UiState(
        val settings: Settings? = null,
        val isSplashVisible: Boolean = true
    )

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        collectSettings()
    }

    private fun collectSettings() = viewModelScope.launch {
        getSettings.invoke().collectLatest { settings ->
            _state.update {
                it.copy(settings = settings)
            }
        }
    }

    fun hideSplash() = _state.update {
        it.copy(
            isSplashVisible = false
        )
    }

}