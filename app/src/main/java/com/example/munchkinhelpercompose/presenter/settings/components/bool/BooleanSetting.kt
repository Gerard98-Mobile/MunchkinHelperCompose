package com.example.munchkinhelpercompose.presenter.settings.components.bool

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.SettingValue
import com.example.munchkinhelpercompose.presenter.settings.components.UiSetting

class BooleanSetting(
    @StringRes val title: Int,
    override val value: Boolean,
    override val onDismissRequest: () -> Unit,
    override val onResult: (Boolean) -> Unit
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
            this,
            onDismissRequest = onDismissRequest
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
