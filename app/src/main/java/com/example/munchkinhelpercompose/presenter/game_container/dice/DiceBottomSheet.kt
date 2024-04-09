package com.example.munchkinhelpercompose.presenter.game_container.dice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.Dice
import com.example.munchkinhelpercompose.ui.components.buttons.MHButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceBottomSheet(
    diceSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onHide: () -> Unit,
) = ModalBottomSheet(
    onDismissRequest = { onHide() },
    sheetState = diceSheetState,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        DiceScreenContent()
    }
}

@Composable
fun DiceScreenContent(
    viewModel : DiceViewModel = viewModel()
) {
    val diceSize = 60.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        val randomW = (0..(constraints.maxWidth - diceSize
                            .toPx()
                            .toInt())).random()
                        val randomH = (0..(constraints.maxHeight - diceSize
                            .toPx()
                            .toInt())).random()

                        placeable.place(randomW, randomH)
                    }
                }
        ) {

            viewModel.dice.collectAsState(initial = null).value?.let {
                key(it.rollCounter) {
                    Dice(
                        value = it.result,
                        modifier = Modifier.size(diceSize)
                    )
                }

            }
        }

        MHButton(
            stringRes = R.string.throw_dice,
        ) {
            // we need to reset value to have another tick if we draw same value
            viewModel.throwDice()
        }
    }
}
