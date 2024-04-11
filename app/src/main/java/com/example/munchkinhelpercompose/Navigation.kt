package com.example.munchkinhelpercompose

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munchkinhelpercompose.presenter.home.HomeScreen
import com.example.munchkinhelpercompose.presenter.new_game.NewGameScreen
import com.example.munchkinhelpercompose.presenter.game_container.GameMainScreen

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
            GameMainScreen()
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
                view.content(navController, it)
            }
        }
    }
}