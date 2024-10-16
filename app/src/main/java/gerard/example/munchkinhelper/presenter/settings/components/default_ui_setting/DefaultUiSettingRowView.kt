package gerard.example.munchkinhelper.presenter.settings.components.default_ui_setting

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
import gerard.example.munchkinhelper.presenter.settings.components.UiSetting
import gerard.example.munchkinhelper.ui.components.SpacerH
import gerard.example.munchkinhelper.ui.theme.MHColor
import gerard.example.munchkinhelper.util.str

@Composable
fun <T> DefaultUiSettingRowView(
    setting: UiSetting<T>,
    onClick: () -> Unit
) = Column (
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(10.dp)
){
    setting.title?.let {
        Text(
            text = it.str(),
            style = MaterialTheme.typography.labelLarge
        )
        SpacerH(dp = 5.dp)
    }
    val valueTextRepresentation = setting.possibleValues.find { it.value == setting.value }?.textRepresentationRes?.str() ?: ""
    Text(
        text = valueTextRepresentation,
        style = MaterialTheme.typography.bodySmall,
        color = MHColor.default_dark
    )
}

@Preview
@Composable
private fun DefaultUiSettingRowViewPreview() {
    DefaultUiSettingRowView(DefaultUiSetting.getPreviewObject()) { }
}