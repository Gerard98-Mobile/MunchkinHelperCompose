package gerard.example.munchkinhelper.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Child have to be an object instance!!!
 */
sealed class NavigationScreen(
    val route: String,
    val content: @Composable (NavController, NavBackStackEntry) -> Unit
) {

    open fun navigate(
        controller: NavController,
        builder: NavOptionsBuilder.() -> Unit = { }
    ) {
        controller.navigate(route, builder)
    }

}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = AppScreen.Home.route) {
        NavigationScreen::class.sealedSubclasses.forEach {
            it.objectInstance?.let { view ->
                composable(
                    route = view.route
                ) {
                    Column(Modifier.fillMaxSize()) {
                        view.content(navController, it)
                    }
                }
            }
        }
    }
}