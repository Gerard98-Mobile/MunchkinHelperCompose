package com.example.munchkinhelpercompose.ui.components.settings.bool

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.settings.SettingValue
import com.example.munchkinhelpercompose.ui.components.settings.UiSetting

class BooleanSetting(
    @StringRes val title: Int,
    override val value: Boolean,
    override val onResult: (Boolean?) -> Unit
) : UiSetting<Boolean>() {
    @Composable
    override fun rowUi(
        onClick: () -> Unit
    ) {
        BooleanSettingRowView(
            setting = this
        ) { onClick() }
    }

    @Composable
    override fun selectUi() {
        BooleanSettingView(
            this
        ) {
            onResult(it)
        }
    }

    override val possibleValues: List<SettingValue<Boolean>>
        get() = listOf(
            SettingValue(true, R.string.enabled),
            SettingValue(false, R.string.disabled)
        )
}
