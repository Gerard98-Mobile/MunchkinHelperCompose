package com.example.munchkinhelpercompose.presenter.settings

import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.UiSetting
import com.example.munchkinhelpercompose.ui.components.bottom_sheets.options.YesNoBottomSheet

sealed class SettingsBottomSheets(
    val content: @Composable () -> Unit
) {

    class Setting(uiSetting: UiSetting<*>): SettingsBottomSheets(
        content = { uiSetting.selectUi() }
    )

    class ResetGame(
        onDismiss: () -> Unit,
        onResult: (Boolean) -> Unit
    ): SettingsBottomSheets(
        content = { ResetGameBottomSheet(onDismiss, onResult) }
    )

}

@Composable
private fun ResetGameBottomSheet(
    onDismiss: () -> Unit,
    onResult: (Boolean) -> Unit
) = YesNoBottomSheet(
    title = R.string.reset_game,
    onDismiss = onDismiss,
    onResult = onResult
)
