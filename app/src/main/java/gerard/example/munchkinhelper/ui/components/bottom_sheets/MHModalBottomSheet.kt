package gerard.example.munchkinhelper.ui.components.bottom_sheets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gerard.example.munchkinhelper.ui.components.SpacerH
import gerard.example.munchkinhelper.util.str
import kotlinx.coroutines.launch

@Composable
fun MHModalBottomSheet(
    @StringRes title: Int?,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) = MHModalBottomSheet(
    title = title?.str(),
    onDismissRequest = onDismissRequest,
    content = content
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MHModalBottomSheet(
    title: String?,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val coroutineScope = rememberCoroutineScope()
    val hideBottomSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
            onDismissRequest()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { hideBottomSheet() },
        sheetState = sheetState
    ) {
        title?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center
            )
        }
        SpacerH(dp = 20.dp)
        Column {
            content(hideBottomSheet)
        }
        SpacerH(dp = 50.dp)
    }
}