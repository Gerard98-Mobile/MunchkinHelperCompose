package com.example.munchkinhelpercompose.presenter.home

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
import com.example.munchkinhelpercompose.Views
import com.example.munchkinhelpercompose.navigate
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
            Views.NEW_GAME.navigate(navController)
        }
        if (state.previousGame != null) {
            MHButton(R.string.load_game) {
                Views.GAME.navigate(navController)
            }
        }
    }
}

