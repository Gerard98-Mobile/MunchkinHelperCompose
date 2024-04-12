package com.example.munchkinhelpercompose.presenter.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.navigation.AppScreen
import com.example.munchkinhelpercompose.ui.Logo
import com.example.munchkinhelpercompose.ui.components.ColumnCentered
import com.example.munchkinhelpercompose.ui.components.buttons.MHButton

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeContent(navController)
}

@Composable
private fun HomeContent(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) = Column(Modifier.fillMaxSize()) {

    val state = viewModel.state.collectAsState().value

    ColumnCentered(
        Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {
        Card {
            Logo()
        }
    }
    ColumnCentered(
        Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {
        MHButton(R.string.new_game) {
            AppScreen.NewGame.navigate(navController)
        }
        AnimatedVisibility(visible = state.previousGame != null) {
            MHButton(R.string.load_game) {
                AppScreen.Game.navigate(navController)
            }
        }
    }
}

