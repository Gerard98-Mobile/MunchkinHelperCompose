package com.example.munchkinhelpercompose.presenter.game_container.fight

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.model.Fight
import com.example.munchkinhelpercompose.model.FightSide
import com.example.munchkinhelpercompose.ui.components.ColumnCentered
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MHToolbarNavigationIcon
import com.example.munchkinhelpercompose.ui.components.SpacerH
import com.example.munchkinhelpercompose.ui.components.SpacerW
import com.example.munchkinhelpercompose.ui.theme.AppTheme

private val CHANGE_POSSIBILITIES = listOf(1, 2, 3, 4, 5, 10, 20)

@Preview
@Composable
private fun FightBottomSheetPreview() {
    AppTheme {
        FightScreenContent()
    }
}

@Composable
fun FightDialog(
    initialValue: Int? = 0,
    viewModel: FightViewModel = hiltViewModel(),
    onHide: () -> Unit,
) {
    LaunchedEffect(null) {
        viewModel.init(initialValue ?: 0)
    }

    Dialog(
        onDismissRequest = { onHide() },
        DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            MHToolbar(
                titleStringRes = R.string.fight,
                navigationIcon = MHToolbarNavigationIcon.Close {
                    onHide()
                }
            )
            FightScreenContent()
        }
    }
}

@Composable
private fun FightScreenContent() {
    SpacerH(dp = 50.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Side(R.string.players, Fight.PLAYER_SIDE)
        Text(
            text = "vs",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            textAlign = TextAlign.Center,
        )
        Side(R.string.monsters, Fight.MONSTER_SIDE)
    }
    SpacerH(dp = 10.dp)


}

@Composable
private fun RowScope.Side(
    @StringRes titleRes: Int,
    side: FightSide,
    viewModel: FightViewModel = hiltViewModel(),
    state: FightViewModel.State = viewModel.state.collectAsState().value
) = ColumnCentered(
    modifier = Modifier
        .weight(3f)
        .width(IntrinsicSize.Max)
) {
    ValueBox(titleRes, state.fight.getValue(side), state.fight.getResult(side))
    SpacerH(dp = 10.dp)
    Counter {
        viewModel.update(it, side)
    }
}

@Composable
private fun ValueBox(@StringRes titleRes: Int, value: Int, fightResult: Fight.Result) = ColumnCentered {

    val animatedColor by animateColorAsState(
        targetValue = fightResult.color,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "color"
    )

    Text(text = stringResource(id = titleRes))
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = animatedColor
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            text = value.toString(),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun Counter(
    change: (Int) -> Unit
) = ColumnCentered {
    CHANGE_POSSIBILITIES.forEach {
        Row(Modifier.fillMaxWidth()) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = { change(it) }) {
                Text(text = "+${it}")
            }
            SpacerW(5.dp)
            OutlinedButton(modifier = Modifier.weight(1f), onClick = { change(it * -1) }) {
                Text(text = "-${it}")
            }
        }

    }
}