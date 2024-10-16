package gerard.example.munchkinhelper.presenter.game_container.dice

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import gerard.example.munchkinhelper.ui.DiceOne

@Composable
fun DiceButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
       DiceOne()
    }
}