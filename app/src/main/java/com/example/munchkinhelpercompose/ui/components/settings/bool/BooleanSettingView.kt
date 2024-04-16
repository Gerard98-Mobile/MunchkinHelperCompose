package com.example.munchkinhelpercompose.ui.components.settings.bool

import androidx.compose.foundation.clickable
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
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.settings.SettingBottomSheet
import com.example.munchkinhelpercompose.util.str

@Preview
@Composable
fun BooleanSettingPreview() {
    BooleanSettingView(
        BooleanSetting(
            title = R.string.settings,
            value = true,
            onResult = { }
        )
    ) { }
}

@Composable
fun BooleanSettingView(
    setting: BooleanSetting,
    onResult: (Boolean?) -> Unit
) {
    SettingBottomSheet(
        title = setting.title,
        onDismissRequest = { onResult(null) }
    ) { hideBottomSheet ->
        BooleanSettingViewContent(setting) {
            hideBottomSheet.invoke()
            onResult(it)
        }
    }
}

@Composable
private fun BooleanSettingViewContent(
    setting: BooleanSetting,
    onSelect: (Boolean) -> Unit
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