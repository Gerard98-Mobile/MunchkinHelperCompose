package gerard.example.munchkinhelper.presenter.manage_game.new_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.navigation.AppScreen
import gerard.example.munchkinhelper.presenter.manage_game.PlayerNameTextField
import gerard.example.munchkinhelper.presenter.manage_game.PlayerRow
import gerard.example.munchkinhelper.presenter.manage_game.rememberPlayerNameTextFieldState
import gerard.example.munchkinhelper.ui.MHIcon
import gerard.example.munchkinhelper.ui.components.MHToolbar
import gerard.example.munchkinhelper.ui.components.MHToolbarNavigationIcon
import gerard.example.munchkinhelper.ui.components.TransparentGradientSpacerH
import gerard.example.munchkinhelper.ui.components.buttons.MHTextButton
import gerard.example.munchkinhelper.util.ifNotEmpty
import gerard.example.munchkinhelper.util.str
import kotlinx.coroutines.flow.collectLatest

private const val MIN_PLAYERS_COUNT = 2

@Composable
fun NewGameScreen(
    navController: NavController,
    viewModel : NewGameViewModel = hiltViewModel()
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(null) {
        viewModel.snackbar.collectLatest {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            NewGameScreenToolbar(navController, viewModel)
        },
        content = {
            Column(Modifier.padding(it)) {
                NewGameScreenContent()
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    )
}

@Composable
private fun NewGameScreenToolbar(
    navController: NavController,
    viewModel : NewGameViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val playersCountError =  R.string.not_enough_players_error.str()
    
    MHToolbar(
        titleStringRes = R.string.new_game,
        navigationIcon = MHToolbarNavigationIcon.Back {
            navController.popBackStack()
        },
        actions = {
            MHTextButton(R.string.start) {
                if (state.players.size >= MIN_PLAYERS_COUNT) {
                    viewModel.createGame()
                    AppScreen.Game.navigate(navController)
                } else {
                    viewModel.showSnackbar(playersCountError)
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NewGameScreenContent(
    viewModel : NewGameViewModel = hiltViewModel()
) = Column {

    val state = viewModel.state.collectAsState().value

    NewPlayerTextField(
        viewModel = viewModel
    )

    NewGameHintBox()

    LazyColumn(
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(bottom = 40.dp)
    ) {
        stickyHeader {
            TransparentGradientSpacerH(dp = 5.dp)
        }

        items(
            items = state.players,
            key = { name -> name }
        ) { name ->
            PlayerRow(
                name = name,
                modifier = Modifier.animateItemPlacement()
            ) {
                viewModel.removePlayer(name)
            }
        }
    }
}

@Composable
private fun NewGameHintBox(
    viewModel: NewGameViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    state.hints.ifNotEmpty { hints ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            LazyRow {
                this.items(hints.toList()) {
                    Hint(it)
                }
            }
        }
    }
}

@Composable
private fun Hint(
    hint: String,
    viewModel: NewGameViewModel = hiltViewModel()
) {
    val playerExistsError = R.string.player_already_exists.str()
    MHTextButton(string = hint) {
        if (viewModel.state.value.players.contains(hint)) {
            viewModel.showSnackbar(playerExistsError)
        }
        else {
            viewModel.addPlayer(hint)
        }
    }
}

@Composable
private fun NewPlayerTextField(
    viewModel : NewGameViewModel
){
    val state = viewModel.state.collectAsState().value

    val textFieldState = rememberPlayerNameTextFieldState()

    fun addPlayer() {
        viewModel.addPlayer(textFieldState.name.value)
        textFieldState.name.value = ""
    }

    PlayerNameTextField(
        players = state.players,
        state = textFieldState,
        trailingIcon = {
            MHIcon.Plus { addPlayer() }
        }
    ) {
        addPlayer()
    }
}