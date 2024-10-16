package gerard.example.munchkinhelper.presenter.game_container.game

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.model.Player
import gerard.example.munchkinhelper.navigation.AppScreen
import gerard.example.munchkinhelper.ui.DiceFive
import gerard.example.munchkinhelper.ui.components.AnimatedCounter
import gerard.example.munchkinhelper.ui.components.MHToolbar
import gerard.example.munchkinhelper.ui.components.MHToolbarNavigationIcon
import gerard.example.munchkinhelper.ui.components.PrimaryBorder
import gerard.example.munchkinhelper.ui.components.SpacerH
import gerard.example.munchkinhelper.ui.components.SpacerW
import gerard.example.munchkinhelper.ui.components.TransparentGradientSpacerH
import gerard.example.munchkinhelper.ui.components.image.ClickableResIcon
import gerard.example.munchkinhelper.ui.components.image.ResIcon
import gerard.example.munchkinhelper.ui.components.text.AutoSizeText
import gerard.example.munchkinhelper.ui.defaults.MHCardDefaults


@Composable
fun GameScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            MHToolbar(
                titleStringRes = R.string.game,
                navigationIcon = MHToolbarNavigationIcon.None,
                actions = {
                    GameScreenToolbarActions(navController)
                }
            )
        },
        content = {
            Column(Modifier.padding(it)) {
                GameScreenContent()
            }
        }
    )
}

@Composable
private fun GameScreenToolbarActions(
    navController: NavController
) {
    ClickableResIcon(
        id = R.drawable.ic_settings
    ) {
        AppScreen.Settings.navigate(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreenContent(
    viewModel: GameViewModel = hiltViewModel(),
    state: GameViewModel.State = viewModel.state.collectAsState().value
) {
    Box {
        Column {
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
                        viewModel.showDialog(GameDialog.Dice())
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

        state.visibleDialog?.let {
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
            viewModel.showDialog(GameDialog.Fight())
        }

        AutoSizeText(
            text = player.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )

        ClickableResIcon(id = R.drawable.ic_grim_reaper) {
            viewModel.showDialog(GameDialog.Death())
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
    player: Player,
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

