package com.example.munchkinhelpercompose.presenter.manage_game

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.ui.components.text.CounterText
import com.example.munchkinhelpercompose.util.str

class PlayerNameTextFieldState(
    var isError: MutableState<Boolean>,
    var name: MutableState<String>,
    val userInteracted: MutableState<Boolean>
)

@Composable
fun rememberPlayerNameTextFieldState(
    isError: MutableState<Boolean> = mutableStateOf(true),
    name: MutableState<String> = mutableStateOf(""),
) = remember {
    PlayerNameTextFieldState(
        isError = isError,
        name = name,
        userInteracted = mutableStateOf(false)
    )
}

@Composable
fun PlayerNameTextField(
    players: List<String>,
    state: PlayerNameTextFieldState = rememberPlayerNameTextFieldState(),
    trailingIcon: @Composable (PlayerNameTextFieldState) -> Unit = { },
    focusAtStart: Boolean = false,
    onCreate: (String) -> Unit,
) {
    fun String.isNameAllowed() = players.all { it != this } && this.length >= Player.MIN_PLAYER_NAME_LENGTH

    val focusRequester = remember { FocusRequester() }

    if (focusAtStart) {
        LaunchedEffect(key1 = null) {
            focusRequester.requestFocus()
        }
    }

    OutlinedTextField(
        value = state.name.value,
        onValueChange = {
            if (it.length <= Player.MAX_PLAYER_NAME_LENGTH) state.name.value = it
            state.userInteracted.value = true
            state.isError.value = !state.name.value.isNameAllowed()
        },
        label = { Text(stringResource(id = R.string.player_name)) },
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .padding(10.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                if (state.name.value.isNameAllowed()) {
                    onCreate.invoke(state.name.value)
                } else {
                    state.isError.value = true
                }
                state.userInteracted.value = true
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            autoCorrect = false
        ),
        trailingIcon = {
            if (state.name.value.isNameAllowed()) {
                trailingIcon(state)
            }
        },
        isError = state.isError.value && state.userInteracted.value,
        supportingText = {
            if (state.isError.value && state.userInteracted.value) ErrorText(R.string.invalid_name)
            else CounterText(state.name.value, Player.MAX_PLAYER_NAME_LENGTH)
        },
        singleLine = true
    )
}

@Composable
private fun ErrorText(@StringRes textRes: Int) {
    Text(
        text = textRes.str(),
        style = MaterialTheme.typography.labelLarge
    )
}