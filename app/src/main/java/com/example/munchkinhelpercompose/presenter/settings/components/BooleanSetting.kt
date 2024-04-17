package com.example.munchkinhelpercompose.presenter.settings.components

import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.default_ui_setting.DefaultUiSetting

private val BOOLEAN_POSSIBLE_SETTING_VALUES = listOf(
    SettingValue(true, R.string.enabled),
    SettingValue(false, R.string.disabled)
)

object BooleanSetting {
    fun create(
        title: Int,
        value: Boolean,
        possibleValues: List<SettingValue<Boolean>> = BOOLEAN_POSSIBLE_SETTING_VALUES,
        onDismiss: () -> Unit,
        onResult: (Boolean) -> Unit
    ) = DefaultUiSetting(
        title = title,
        value = value,
        onDismiss = onDismiss,
        onResult = onResult,
        possibleValues = possibleValues
    )
}