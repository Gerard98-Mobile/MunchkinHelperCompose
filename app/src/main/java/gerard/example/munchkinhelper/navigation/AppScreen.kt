package gerard.example.munchkinhelper.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import gerard.example.munchkinhelper.presenter.manage_game.edit_game.EditGameScreen
import gerard.example.munchkinhelper.presenter.game_container.game.GameScreen
import gerard.example.munchkinhelper.presenter.home.HomeScreen
import gerard.example.munchkinhelper.presenter.manage_game.new_game.NewGameScreen
import gerard.example.munchkinhelper.presenter.settings.SettingsScreen

object AppScreen {
    object Home : NavigationScreen(
        route = "home",
        content = { controller, _ ->
            HomeScreen(controller)
        }
    )

    object NewGame : NavigationScreen(
        route = "new_game",
        content = { controller, _ ->
            NewGameScreen(controller)
        }
    )

    object Game : NavigationScreen(
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

    object Settings : NavigationScreen(
        route = "settings",
        content = { controller, _ ->
            SettingsScreen(controller)
        }
    )

    object EditGame : NavigationScreen(
        route = "edit_game",
        content = { controller, _ ->
            EditGameScreen(controller)
        }
    )
}