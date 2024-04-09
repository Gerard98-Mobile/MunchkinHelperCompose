package com.example.munchkinhelpercompose.presenter.game_container

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MhToolbarNavigationIcon

@Composable
fun GameMainScreen(
    parentNavController: NavController
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
          MHToolbar(navController = parentNavController, titleStringRes = R.string.game, navigationIcon = MhToolbarNavigationIcon.NONE)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GameNavigation.GAME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            GameNavigation.values().forEach { view ->
                composable(
                    route = view.route,
                ) {
                    view.content(navController, it)
                }
            }
        }
    }
}