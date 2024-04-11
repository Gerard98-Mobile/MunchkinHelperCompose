package com.example.munchkinhelpercompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munchkinhelpercompose.presenter.game_container.game.GameScreen
import com.example.munchkinhelpercompose.presenter.home.HomeScreen
import com.example.munchkinhelpercompose.presenter.new_game.NewGameScreen

enum class Views(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavController, NavBackStackEntry) -> Unit
) {
    HOME(
        route = "home",
        content = { controller, _ ->
            HomeScreen(controller)
        }
    ),
    NEW_GAME(
        route = "new_game",
        content = { controller, _ ->
            NewGameScreen(controller)
        }
    ),
    GAME(
        route = "game",
        content = { _, _ ->
            GameScreen()
        }
    )
}

fun NavController.navigate(view: Views) = this.navigate(view.route)
fun Views.navigate(controller: NavController) = controller.navigate(this.route)

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Views.HOME.route) {
        Views.values().forEach { view ->
            composable(
                route = view.route,
                arguments = view.arguments
            ) {
                Column(Modifier.fillMaxSize()) {
                    view.content(navController, it)
                }
            }
        }
    }
}