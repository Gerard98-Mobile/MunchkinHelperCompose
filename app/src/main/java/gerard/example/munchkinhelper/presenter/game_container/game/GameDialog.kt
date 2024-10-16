package gerard.example.munchkinhelper.presenter.game_container.game

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import gerard.example.munchkinhelper.model.Player
import gerard.example.munchkinhelper.presenter.game_container.death.DeathBottomSheet
import gerard.example.munchkinhelper.presenter.game_container.dice.DiceBottomSheet
import gerard.example.munchkinhelper.presenter.game_container.fight.FightDialog
import gerard.example.munchkinhelper.presenter.game_container.winner.WinnerDialog

sealed class GameDialog(
    val content: @Composable (GameViewModel) -> Unit
) {

    class Death: GameDialog(
        content = { DeathDialogContent() }
    )

    class Dice: GameDialog(
        content = { DiceDialogContent() }
    )

    class Fight: GameDialog(
        content = { FightDialogContent() }
    )

    class Winner(
        player: Player
    ) : GameDialog(
        content = { WinnerDialogContent(player) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeathDialogContent(
    viewModel: GameViewModel = hiltViewModel()
){
    DeathBottomSheet(viewModel.state.value.selected?.name ?: "") {
        viewModel.hideDialog()
        if (it.killPlayer) {
            viewModel.update {
                this.kill()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiceDialogContent(
    viewModel: GameViewModel = hiltViewModel()
){
    DiceBottomSheet {
        viewModel.hideDialog()
    }
}

@Composable
private fun FightDialogContent(
    viewModel: GameViewModel = hiltViewModel()
){
    FightDialog(
        initialValue = viewModel.state.value.selected?.powerWithLevel
    ) {
        viewModel.hideDialog()
    }
}

@Composable
private fun WinnerDialogContent(
    player: Player,
    viewModel: GameViewModel = hiltViewModel()
){
    WinnerDialog(
        winnerName = player.name
    ) {
        viewModel.hideDialog()
    }
}