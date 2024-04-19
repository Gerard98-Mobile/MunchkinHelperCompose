package com.example.munchkinhelpercompose.presenter.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.SettingBottomSheet
import com.example.munchkinhelpercompose.presenter.settings.components.UiSetting
import com.example.munchkinhelpercompose.util.str

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
){
    SettingBottomSheet(title = R.string.reset_game, onDismissRequest = { onDismiss() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    it.invoke()
                    onResult(true)
                }
                .padding(10.dp)
        ) {
            Text(
                text = R.string.yes.str()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    it.invoke()
                    onResult(false)
                }
                .padding(10.dp)
        ) {
            Text(
                text = R.string.no.str()
            )
        }
    }
}
