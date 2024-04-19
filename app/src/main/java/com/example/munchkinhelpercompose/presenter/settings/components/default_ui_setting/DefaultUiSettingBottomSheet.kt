package com.example.munchkinhelpercompose.presenter.settings.components.default_ui_setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.presenter.settings.components.UiSetting
import com.example.munchkinhelpercompose.ui.components.bottom_sheets.MHModalBottomSheet
import com.example.munchkinhelpercompose.util.str

@Composable
fun <T> DefaultUiSettingBottomSheet(
    setting: UiSetting<T>,
) = Column {
    MHModalBottomSheet(
        title = setting.title,
        onDismissRequest = { setting.onDismiss.invoke() }
    ) { hideBottomSheet ->
        DefaultUiSettingViewContent(setting) {
            hideBottomSheet.invoke()
            setting.onResult.invoke(it)
        }
    }
}

@Composable
private fun <T> DefaultUiSettingViewContent(
    setting: UiSetting<T>,
    onSelect: (T) -> Unit
) = LazyColumn {
    items(setting.possibleValues) { item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect(item.value) }
                .padding(10.dp)
        ) {
            Text(
                text = item.textRepresentationRes.str()
            )
        }
    }
}

@Preview
@Composable
private fun DefaultUiSettingViewContentPreview() {
    DefaultUiSettingViewContent(DefaultUiSetting.getPreviewObject()) { }
}