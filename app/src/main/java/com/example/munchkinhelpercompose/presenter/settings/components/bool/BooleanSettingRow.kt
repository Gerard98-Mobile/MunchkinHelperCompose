package com.example.munchkinhelpercompose.presenter.settings.components.bool

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.SpacerH
import com.example.munchkinhelpercompose.util.str

@Preview
@Composable
fun BooleanSettingRowPreview() {
    BooleanSettingRowView(
        BooleanSetting(
            title = R.string.settings,
            value = true,
            onDismissRequest = { }
        ) { }
    ) {

    }
}

@Composable
fun BooleanSettingRowView(
    setting: BooleanSetting,
    onClick: () -> Unit
) = Column (
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(10.dp)
){
    Text(
        text = setting.title.str(),
        style = MaterialTheme.typography.labelLarge
    )
    SpacerH(dp = 5.dp)
    val valueTextRepresentation = (if (setting.value) R.string.enabled else R.string.disabled).str()
    Text(
        text = valueTextRepresentation,
        style = MaterialTheme.typography.bodySmall
    )
}