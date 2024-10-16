package gerard.example.munchkinhelper.ui.components.bottom_sheets.options

import androidx.compose.runtime.Composable
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.util.str

@Composable
fun YesNoBottomSheet(
    title: String,
    onDismiss: () -> Unit,
    onResult: (Boolean) -> Unit
) = OptionsBottomSheet(
    title = title,
    options = listOf(
        Option(
            text = R.string.yes.str(),
            onSelect = { onResult(true) }
        ),
        Option(
            text = R.string.no.str(),
            onSelect = { onResult(false) }
        )
    ),
    onDismiss = onDismiss
)
