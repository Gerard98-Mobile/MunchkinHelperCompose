package gerard.example.munchkinhelper.presenter.manage_game.edit_game.bottom_sheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.presenter.manage_game.PlayerNameTextField
import gerard.example.munchkinhelper.presenter.manage_game.rememberPlayerNameTextFieldState
import gerard.example.munchkinhelper.ui.components.bottom_sheets.MHModalBottomSheet
import gerard.example.munchkinhelper.ui.components.buttons.MHButton

@Composable
fun PlayerNameBottomSheet(
    title: String,
    occupiedPlayerNames: List<String>,
    onResult: (String) -> Unit,
    onDismiss: () -> Unit = {},
) {

    val textFieldState = rememberPlayerNameTextFieldState()

    MHModalBottomSheet(
        title = title,
        onDismissRequest = { onDismiss() },
    ) { hide ->
        PlayerNameTextField(
            players = occupiedPlayerNames,
            state = textFieldState,
            focusAtStart = true,
        ) {
            hide.invoke()
            onResult(it)
        }

        MHButton(
            stringRes = R.string.confirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            enabled = !textFieldState.isError.value
        ) {
            hide.invoke()
            onResult(textFieldState.name.value)
        }
    }
}