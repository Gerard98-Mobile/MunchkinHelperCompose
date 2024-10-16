package gerard.example.munchkinhelper.presenter.manage_game.edit_game.bottom_sheet

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.model.Player
import gerard.example.munchkinhelper.presenter.manage_game.edit_game.EditGameViewModel
import gerard.example.munchkinhelper.ui.components.bottom_sheets.options.YesNoBottomSheet
import gerard.example.munchkinhelper.util.str

sealed class EditGameBottomSheet(
    val content: @Composable (EditGameViewModel) -> Unit
) {

    class RemovePlayer(player: Player) : EditGameBottomSheet(
        content = { RemovePlayerBottomSheet(player) }
    )

    class EditPlayerName(
        player: Player,
        onResult: (String) -> Unit
    ) : EditGameBottomSheet(
        content = {
            EditPlayerNameContent(player, it, onResult)
        }
    )

    class CreatePlayer(
        onResult: (String) -> Unit
    ) : EditGameBottomSheet(
        content = {
            CreatePlayerContent(it, onResult)
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
    viewModel: EditGameViewModel = hiltViewModel(),
    onResult: (String) -> Unit
){
    PlayerNameBottomSheet(
        title = R.string.editing.str(player.name),
        occupiedPlayerNames = viewModel.state.value.game?.players?.map { it.name } ?: emptyList(),
        onDismiss = { viewModel.changeVisibleBottomSheet(null) },
        onResult = onResult
    )
}

@Composable
private fun CreatePlayerContent(
    viewModel: EditGameViewModel = hiltViewModel(),
    onResult: (String) -> Unit
){
    PlayerNameBottomSheet(
        title = R.string.add_player.str(),
        occupiedPlayerNames = viewModel.state.value.game?.players?.map { it.name } ?: emptyList(),
        onDismiss = { viewModel.changeVisibleBottomSheet(null) },
        onResult = onResult
    )
}
