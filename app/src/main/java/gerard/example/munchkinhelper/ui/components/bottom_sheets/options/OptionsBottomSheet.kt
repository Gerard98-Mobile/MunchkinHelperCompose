package gerard.example.munchkinhelper.ui.components.bottom_sheets.options

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
import gerard.example.munchkinhelper.ui.components.bottom_sheets.MHModalBottomSheet

data class Option(
    val text: String,
    val onSelect: () -> Unit
)

@Composable
fun OptionsBottomSheet(
    title: String,
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
                        text = option.text
                    )
                }
            }
        }
    }
}