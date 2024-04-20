package com.example.munchkinhelpercompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.munchkinhelpercompose.presenter.game_container.game.GameScreen
import com.example.munchkinhelpercompose.presenter.home.HomeScreen
import com.example.munchkinhelpercompose.presenter.new_game.NewGameScreen
import com.example.munchkinhelpercompose.presenter.settings.SettingsScreen

object AppScreen {
    object Home : NavigationView(
        route = "home",
        content = { controller, _ ->
            HomeScreen(controller)
        }
    )

    object NewGame : NavigationView(
        route = "new_game",
        content = { controller, _ ->
            NewGameScreen(controller)
        }
    )

    object Game : NavigationView(
        route = "game",
        content = { controller, _ ->
            GameScreen(controller)
        }
    ) {
        override fun navigate(controller: NavController, builder: NavOptionsBuilder.() -> Unit) {
            controller.navigate(route) {
                popUpTo(0)
            }
        }
    }

    object Settings : NavigationView(
        route = "settings",
        content = { controller, _ ->
            SettingsScreen(controller)
        }
    )
}