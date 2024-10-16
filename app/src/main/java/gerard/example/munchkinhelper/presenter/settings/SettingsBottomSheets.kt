package gerard.example.munchkinhelper.presenter.settings

import androidx.compose.runtime.Composable
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.presenter.settings.components.UiSetting
import gerard.example.munchkinhelper.ui.components.bottom_sheets.options.YesNoBottomSheet
import gerard.example.munchkinhelper.util.str

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
    title = R.string.reset_game.str(),
    onDismiss = onDismiss,
    onResult = onResult
)
