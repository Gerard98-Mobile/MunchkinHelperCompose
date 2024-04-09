package com.example.munchkinhelpercompose.presenter.game_container

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.presenter.game_container.game.GameScreen
import com.example.munchkinhelpercompose.presenter.game_container.winner.WinnerScreen

enum class GameNavigation(
    val route: String,
    val content: @Composable (NavController, NavBackStackEntry) -> Unit
) {

    GAME(
        route = "game",
        content = { nav, backStack -> GameScreen(nav) }
    ),
    WIN(
        route = "winner",
        content = { nav, backStack -> WinnerScreen(nav) }
    )

}