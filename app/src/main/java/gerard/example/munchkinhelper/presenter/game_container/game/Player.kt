package gerard.example.munchkinhelper.presenter.game_container.game

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.model.Player
import gerard.example.munchkinhelper.ui.components.PrimaryBorder
import gerard.example.munchkinhelper.ui.components.SpacerW
import gerard.example.munchkinhelper.ui.components.image.ResIcon
import gerard.example.munchkinhelper.ui.components.image.ResImage
import gerard.example.munchkinhelper.ui.theme.MHColor
import gerard.example.munchkinhelper.util.str
import kotlinx.coroutines.flow.collectLatest

private val PLAYER_ENTRY_PADDING = 10.dp

@Composable
fun PlayersHeader() = Column(
    modifier = Modifier
        .fillMaxWidth()
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = PLAYER_ENTRY_PADDING, end = PLAYER_ENTRY_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = R.string.power.str(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = R.string.level.str(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        ResIcon(
            id = R.drawable.ic_grim_reaper,
            modifier = Modifier
                .size(26.dp)
                .weight(0.1f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Player(
    player: Player,
    isSelected: Boolean,
    viewModel: GameViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState().value
    val isLeader = state.isLeader(player)

    val baseColor = MaterialTheme.colorScheme.primaryContainer
    val color = remember { Animatable(baseColor) }

    if (isLeader) {
        LaunchedEffect(null) {
            color.animateTo(MHColor.crown, animationSpec = tween(500))
            color.animateTo(baseColor, animationSpec = tween(500))
        }
    }
    else if (color.value != baseColor && (color.targetValue == baseColor || color.targetValue == MHColor.crown)) {
        LaunchedEffect(null) {
            color.animateTo(baseColor)
        }
    }

    LaunchedEffect(null) {
        viewModel.killed.collectLatest {
            if (it?.name == player.name) {
                color.animateTo(MHColor.default_dark, animationSpec = tween(500))
                color.animateTo(baseColor, animationSpec = tween(500))
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        colors = CardDefaults.cardColors(containerColor = color.value),
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
    Row(Modifier.padding(PLAYER_ENTRY_PADDING)) {
        Row(Modifier.weight(0.5f)) {
            if (isLeader) {
                ResImage(
                    id = R.drawable.crown,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
                SpacerW(dp = 10.dp)
            }
            Text(
                text = player.name,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = player.powerWithLevel.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = player.level.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = player.deaths.toString(),
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center,
            color = MHColor.default_dark
        )

    }
}