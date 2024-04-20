package com.example.munchkinhelpercompose.presenter.settings.components.default_ui_setting

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.SettingValue
import com.example.munchkinhelpercompose.presenter.settings.components.UiSetting

class DefaultUiSetting <T>(
    @StringRes override val title: Int?,
    override val onDismiss: () -> Unit,
    override val value: T,
    override val possibleValues: List<SettingValue<T>>,
    override val onResult: (T) -> Unit,
) : UiSetting<T>() {

    companion object {
        fun getPreviewObject() = DefaultUiSetting(
            title = R.string.settings,
            onResult = { },
            onDismiss = { },
            value = true,
            possibleValues = listOf(
                SettingValue(true, R.string.enabled),
                SettingValue(false, R.string.disabled)
            )
        )
    }

    @Composable
    override fun rowUi(onClick: () -> Unit) = DefaultUiSettingRowView(this, onClick)

    @Composable
    override fun selectUi() = DefaultUiSettingBottomSheet(this)
}