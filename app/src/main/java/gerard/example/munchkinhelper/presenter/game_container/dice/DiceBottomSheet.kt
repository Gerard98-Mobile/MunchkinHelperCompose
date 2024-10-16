package gerard.example.munchkinhelper.presenter.game_container.dice

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.Dice
import gerard.example.munchkinhelper.ui.components.buttons.MHButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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
    viewModel : DiceViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val diceSize = 60.dp
    val diceAlpha = remember { Animatable(1f) }

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

                    LaunchedEffect(null) {
                        diceAlpha.animateTo(1f, animationSpec = tween(250))
                    }

                    Dice(
                        value = it.result,
                        modifier = Modifier
                            .size(diceSize)
                            .alpha(diceAlpha.value)
                    )
                }
            }
        }

        MHButton(
            stringRes = R.string.throw_dice,
        ) {
            scope.launch {
                diceAlpha.animateTo(0f, animationSpec = tween(250))
                viewModel.throwDice()
            }
        }
    }
}
