package com.example.munchkinhelpercompose.presenter.manage_game.edit_game.bottom_sheet

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.presenter.manage_game.edit_game.EditGameViewModel
import com.example.munchkinhelpercompose.ui.components.bottom_sheets.options.YesNoBottomSheet
import com.example.munchkinhelpercompose.util.str

sealed class EditGameBottomSheet(
    val content: @Composable (EditGameViewModel) -> Unit
) {

    class RemovePlayer(player: Player) : EditGameBottomSheet(
        content = { RemovePlayerBottomSheet(player) }
    )

    class EditPlayerName(
        player: Player,
    ) : EditGameBottomSheet(
        content = {
            EditPlayerNameContent(player, it)
        }
    )

}

@Composable
private fun RemovePlayerBottomSheet(
    player: Player,
    viewModel: EditGameViewModel = hiltViewModel()
){
    YesNoBottomSheet(
        title = R.string.remove_player.str(player.name),
        onDismiss = { viewModel.changeVisibleBottomSheet(null) },
        onResult = {
            if (it) viewModel.removePlayer(player)
        }
    )
}

@Composable
private fun EditPlayerNameContent(
    player: Player,
    viewModel: EditGameViewModel = hiltViewModel()
){
    EditPlayerNameBottomSheet(
        player = player,
        onDismiss = { viewModel.changeVisibleBottomSheet(null) }
    )
}
