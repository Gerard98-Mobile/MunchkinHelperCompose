package com.example.munchkinhelpercompose.ui.components.bottom_sheets.options

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.ui.components.bottom_sheets.MHModalBottomSheet
import com.example.munchkinhelpercompose.util.str

data class Option(
    @StringRes val valueStringRes: Int,
    val onSelect: () -> Unit
)

@Composable
fun OptionsBottomSheet(
    @StringRes title: Int,
    options: List<Option>,
    onDismiss: () -> Unit,
){
    MHModalBottomSheet(title = title, onDismissRequest = { onDismiss() }) {
        LazyColumn {
            items(options) { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDismiss.invoke()
                            option.onSelect.invoke()
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = option.valueStringRes.str()
                    )
                }
            }
        }
    }
}