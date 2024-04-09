package com.example.munchkinhelpercompose.presenter.game_container.dice

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.ui.DiceOne

@Composable
fun DiceButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
       DiceOne()
    }
}