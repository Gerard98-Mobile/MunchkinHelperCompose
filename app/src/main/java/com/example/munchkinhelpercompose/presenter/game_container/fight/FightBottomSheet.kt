package com.example.munchkinhelpercompose.presenter.game_container.fight

import androidx.annotation.StringRes
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.ColumnCentered
import com.example.munchkinhelpercompose.ui.components.SpacerH
import com.example.munchkinhelpercompose.ui.components.SpacerW
import kotlinx.coroutines.CoroutineScope

private val CHANGE_POSSIBILITIES = listOf(1, 2, 3, 4, 5, 10, 20)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FightBottomSheet(
    initialValue: Int? = 0,
    fightSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onHide: () -> Unit,
) = ModalBottomSheet(
    onDismissRequest = { onHide() },
    sheetState = fightSheetState,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        FightScreenContent(initialValue ?: 0)
    }
}

@Composable
private fun FightScreenContent(
    initialValue: Int
) {
    SpacerH(dp = 50.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Side(R.string.players, initialValue)
        Text(
            text = "vs",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            textAlign = TextAlign.Center,
        )
        Side(R.string.monsters)
    }
    SpacerH(dp = 10.dp)


}

@Composable
private fun RowScope.Side(@StringRes titleRes: Int, initialValue: Int = 0) = ColumnCentered(
    modifier = Modifier
        .weight(3f)
        .width(IntrinsicSize.Max)
) {
    val power = remember { mutableStateOf(initialValue) }

    ValueBox(titleRes, power.value)
    SpacerH(dp = 10.dp)
    Counter {
        power.value += it
    }
}

@Composable
private fun ValueBox(@StringRes titleRes: Int, value: Int) = ColumnCentered {
    Text(text = stringResource(id = titleRes))
    Card {
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