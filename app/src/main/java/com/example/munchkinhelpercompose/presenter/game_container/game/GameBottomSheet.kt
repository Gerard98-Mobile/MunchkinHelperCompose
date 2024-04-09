package com.example.munchkinhelpercompose.presenter.game_container.game

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.presenter.game_container.death.DeathBottomSheet
import com.example.munchkinhelpercompose.presenter.game_container.dice.DiceBottomSheet
import com.example.munchkinhelpercompose.presenter.game_container.fight.FightBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
enum class GameBottomSheet(val content: @Composable (GameViewModel) -> Unit) {
    DEATH(
        content = { viewModel ->
            DeathBottomSheet(viewModel.state.value.selected?.name ?: "") {
                viewModel.hideBottomSheet()
                viewModel.update {
                    copy(
                        deaths = this.deaths + 1
                    )
                }
            }
        }
    ),
    DICE(
        content = {
            DiceBottomSheet {
                it.hideBottomSheet()
            }
        }
    ),
    FIGHT(
        content = {
            FightBottomSheet(initialValue = it.state.value.selected?.powerWithLevel) {
                it.hideBottomSheet()
            }
        }
    ),
}