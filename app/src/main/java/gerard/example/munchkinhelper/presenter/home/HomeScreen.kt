package gerard.example.munchkinhelper.presenter.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.navigation.AppScreen
import gerard.example.munchkinhelper.ui.Logo
import gerard.example.munchkinhelper.ui.components.ColumnCentered
import gerard.example.munchkinhelper.ui.components.buttons.MHButton

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
    val state by viewModel.state.collectAsState()

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

