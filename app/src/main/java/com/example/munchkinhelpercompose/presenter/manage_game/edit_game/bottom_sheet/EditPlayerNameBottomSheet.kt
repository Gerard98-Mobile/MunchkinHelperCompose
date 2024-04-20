package com.example.munchkinhelpercompose.presenter.manage_game.edit_game.bottom_sheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.presenter.manage_game.CreatePlayerTextField
import com.example.munchkinhelpercompose.presenter.manage_game.edit_game.EditGameViewModel
import com.example.munchkinhelpercompose.presenter.manage_game.rememberCreatePlayerTextFieldState
import com.example.munchkinhelpercompose.ui.components.bottom_sheets.MHModalBottomSheet
import com.example.munchkinhelpercompose.ui.components.buttons.MHButton
import com.example.munchkinhelpercompose.util.str

@Composable
fun EditPlayerNameBottomSheet(
    player: Player,
    viewModel: EditGameViewModel = hiltViewModel(),
    onDismiss: () -> Unit = {},
) {
    val state = viewModel.state.value

    val textFieldState = rememberCreatePlayerTextFieldState()

    MHModalBottomSheet(
        title = R.string.editing.str(player.name),
        onDismissRequest = { onDismiss() },
    ) { hide ->
        CreatePlayerTextField(
            players = state.game?.players?.toList()?.map { it.name } ?: emptyList(),
            state = textFieldState,
            focusAtStart = true,
        ) {
            hide.invoke()
            viewModel.changePlayerName(player, it)
        }

        MHButton(
            stringRes = R.string.confirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            enabled = !textFieldState.isError.value
        ) {
            hide.invoke()
            viewModel.changePlayerName(player, textFieldState.name.value)
        }
    }
}