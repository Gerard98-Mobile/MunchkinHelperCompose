package com.example.munchkinhelpercompose.presenter.game_container

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MhToolbarNavigationIcon
import com.example.munchkinhelpercompose.ui.components.image.ResIcon

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