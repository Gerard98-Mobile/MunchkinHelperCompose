package com.example.munchkinhelpercompose.presenter.game_container.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.ui.components.PrimaryBorder
import com.example.munchkinhelpercompose.ui.components.SpacerW
import com.example.munchkinhelpercompose.ui.components.image.ResImage

@Composable
fun PlayersHeader() = Column(
    modifier = Modifier
        .fillMaxWidth()
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 5.dp),
    ) {
        Spacer(modifier = Modifier.weight(6f))
        PlayerText(
            text = stringResource(id = R.string.power),
            weight = 2f,
            padding = 0.dp,
            textAlign = TextAlign.Center
        )
        PlayerText(
            text = stringResource(id = R.string.level),
            weight = 2f,
            padding = 0.dp,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Player(
    player: Player,
    isSelected: Boolean,
    viewModel: GameViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState().value
    val isLeader = state.isLeader(player)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        border = if (isSelected) PrimaryBorder() else null,
        content = { PlayerContent(player, isLeader) },
        onClick = { viewModel.select(player) }
    )
}

@Composable
private fun PlayerContent(
    player: Player,
    isLeader: Boolean
) {
    Row {
        if (isLeader) {
            SpacerW(dp = 10.dp)

            ResImage(
                id = R.drawable.crown,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        PlayerText(
            text = player.name, weight = 0.6f
        )
        PlayerText(
            text = player.powerWithLevel.toString(), weight = 0.2f, textAlign = TextAlign.Center
        )
        PlayerText(
            text = player.level.toString(), weight = 0.2f, textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowScope.PlayerText(
    text: String,
    weight: Float,
    padding: Dp = 10.dp,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        textAlign = textAlign,
        modifier = Modifier
            .padding(padding)
            .weight(weight)
    )
}