package com.example.munchkinhelpercompose.presenter.new_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.Views
import com.example.munchkinhelpercompose.navigate
import com.example.munchkinhelpercompose.ui.Plus
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MhToolbarNavigationIcon
import com.example.munchkinhelpercompose.ui.components.buttons.MHTextButton
import com.example.munchkinhelpercompose.util.ifNotEmpty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val MAX_PLAYER_NAME_LENGTH = 20
private const val MIN_PLAYERS_COUNT = 2

@Composable
fun NewGameScreen(
    navController: NavController
){
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            NewGameScreenToolbar(navController, snackbarHostState)
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
    snackbarHostState: SnackbarHostState,
    scope : CoroutineScope = rememberCoroutineScope(),
    viewModel : NewGameViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    val playersCountError = stringResource(id = R.string.not_enough_players_error)
    
    MHToolbar(
        navController = navController,
        titleStringRes = R.string.new_game,
        navigationIcon = MhToolbarNavigationIcon.BACK,
        actions = {
            MHTextButton(R.string.start) {
                if (state.players.size >= MIN_PLAYERS_COUNT) {
                    viewModel.saveHint()
                    viewModel.createGame()
                    Views.GAME.navigate(navController)
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(playersCountError)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NewGameScreenContent(
    viewModel : NewGameViewModel = viewModel()
) = Column {

    val state = viewModel.state.collectAsState().value

    NewPlayerTextField(
        viewModel = viewModel
    )

    NewGameHintBox()

    LazyColumn {
        this.items(
            items = state.players,
            key = { name -> name }
        ) { name ->
            Player(
                name = name,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun NewGameHintBox(
    viewModel: NewGameViewModel = viewModel()
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
    viewModel: NewGameViewModel = viewModel()
) {
    MHTextButton(string = hint) {
        viewModel.addPlayer(hint)
    }
}

@Composable
private fun NewPlayerTextField(
    viewModel : NewGameViewModel
){
    val state = viewModel.state.collectAsState().value

    var playerName by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = playerName,
        onValueChange = {
            if (it.length <= MAX_PLAYER_NAME_LENGTH) playerName = it
            viewModel.changeErrorState(null)
        },
        label = { Text(stringResource(id = R.string.player_name)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.addPlayer(playerName)
                playerName = ""
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            autoCorrect = false
        ),
        trailingIcon = {
            Plus {
                viewModel.addPlayer(playerName)
                playerName = ""
            }
        },
        isError = state.error != null,
        supportingText = {
            state.error.let {
                if (it != null) Error(it)
                else Counter(playerName)
            }

        },
        singleLine = true
    )
}

@Composable
private fun Error(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
private fun Counter(text: String) {
    Text(
        text = "${text.length} / $MAX_PLAYER_NAME_LENGTH",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
    )
}

@Composable
@Preview
fun PlayerPreview() {
    Player(name = "Test")
}

@Composable
private fun Player(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
