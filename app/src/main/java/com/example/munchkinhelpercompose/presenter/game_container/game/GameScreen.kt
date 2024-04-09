package com.example.munchkinhelpercompose.presenter.game_container.game

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.defaults.MHCardDefaults
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.ui.DiceFive
import com.example.munchkinhelpercompose.ui.components.AnimatedCounter
import com.example.munchkinhelpercompose.ui.components.AutoSizeText
import com.example.munchkinhelpercompose.ui.components.PrimaryBorder
import com.example.munchkinhelpercompose.ui.components.SpacerH
import com.example.munchkinhelpercompose.ui.components.SpacerW
import com.example.munchkinhelpercompose.ui.components.TransparentGradientSpacerH
import com.example.munchkinhelpercompose.ui.components.image.ClickableResIcon
import com.example.munchkinhelpercompose.ui.components.image.ResIcon
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel(),
    state: GameViewModel.State = viewModel.state.collectAsState().value
) {
    LaunchedEffect(null) {
        viewModel.winner.collectLatest {
//            navController.navigate("winner")
        }
    }

    Box {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxHeight(),
                    contentPadding = PaddingValues(bottom = 68.dp)
                ) {
                    stickyHeader {
                        PlayersHeader()
                        TransparentGradientSpacerH(dp = 5.dp)
                    }

                    items(state.game?.players?.toList() ?: emptyList()) { item ->
                        Player(
                            item,
                            state.selected == item
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                    colors = MHCardDefaults.transparentCardColors(),
                    onClick = {
                        viewModel.showBottomSheet(GameBottomSheet.DICE)
                    }
                ) {
                    DiceFive(Modifier.size(48.dp))
                }
            }

            if (state.selected != null) {
                Box {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    SelectedPlayerBox(state.selected)
                }
            }
        }

        state.visibleBottomSheet?.let {
            it.content(viewModel)
        }
    }
}

@Composable
private fun SelectedPlayerBox(
    player: Player,
    viewModel: GameViewModel = hiltViewModel(),
) = Column(
    Modifier
        .fillMaxWidth()
        .padding(10.dp)
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableResIcon(id = R.drawable.ic_swords) {
            viewModel.showBottomSheet(GameBottomSheet.FIGHT)
        }

        AutoSizeText(
            text = player.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )

        ClickableResIcon(id = R.drawable.ic_grim_reaper) {
            viewModel.showBottomSheet(GameBottomSheet.DEATH)
        }

    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        UpdatePlayerBox(
            headerResId = R.string.power,
            value = { it.powerWithLevel },
            onChange = {
                viewModel.update {
                    this.copy(power = power + it)
                }
            },
            player = player
        )

        UpdatePlayerBox(
            headerResId = R.string.level,
            value = { it.level },
            onChange = {
                viewModel.update {
                    this.copy(level = level + it)
                }
            },
            player = player
        )
    }
    
    SpacerH(dp = 20.dp)
}

@Composable
private fun RowScope.UpdatePlayerBox(
    @StringRes headerResId: Int,
    value: (Player) -> Int,
    onChange: (Int) -> Unit,
    player: Player
) = Column(
    modifier = Modifier.weight(1f),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(id = headerResId),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
    )

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        ModificationButton(R.drawable.ic_minus) {
            onChange(-1)
        }

        SpacerW(dp = 5.dp)

        AnimatedCounter(
            value = value(player),
            modifier = Modifier.defaultMinSize(minWidth = 40.dp)
        )

        SpacerW(dp = 5.dp)

        ModificationButton(R.drawable.ic_add) {
            onChange(+1)
        }
    }
}

@Composable
private fun ModificationButton(
    @DrawableRes drawableRes: Int,
    onClick: () -> Unit
) {
    Card(
        border = PrimaryBorder(),
        onClick = onClick
    ) {
        ResIcon(
            modifier = Modifier.padding(12.dp),
            id = drawableRes
        )
    }
}

