package com.example.munchkinhelpercompose.presenter.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munchkinhelpercompose.model.Settings
import com.example.munchkinhelpercompose.ui.components.settings.UiSetting
import com.example.munchkinhelpercompose.use_case.settings.GetSettingsUseCase
import com.example.munchkinhelpercompose.use_case.settings.UpdateSettingsUseCase
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
    private val updateSettings: UpdateSettingsUseCase
) : ViewModel() {

    data class State(
        val settings: Settings = Settings(),
        val editingSetting: UiSetting<*>? = null
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

    fun editSetting(setting: UiSetting<*>?) = _state.update {
        it.copy(
            editingSetting = setting
        )
    }

    fun updateSettings(func: (Settings) -> Settings) = viewModelScope.launch {
        val newSettings = func.invoke(state.value.settings).let(func)
        updateSettings.invoke(newSettings)
    }

}