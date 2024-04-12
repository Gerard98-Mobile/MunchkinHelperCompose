package com.example.munchkinhelpercompose.presenter.game_container.death

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.SpacerH
import com.example.munchkinhelpercompose.ui.components.buttons.MHButton
import com.example.munchkinhelpercompose.ui.components.image.ResIcon
import com.example.munchkinhelpercompose.ui.theme.MHColor
import com.example.munchkinhelpercompose.util.str

data class DeathResult(
    val killPlayer: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeathBottomSheet(
    playerName: String,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onHide: (DeathResult) -> Unit,
) = ModalBottomSheet(
    onDismissRequest = { onHide(DeathResult()) },
    sheetState = sheetState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Content(playerName, onHide)
    }
}


@Composable
private fun Content(
    playerName: String,
    onHide: (DeathResult) -> Unit,
) {
    ResIcon(
        id = R.drawable.ic_grave,
        modifier = Modifier.size(150.dp)
    )
    SpacerH(dp = 30.dp)
    Text(
        text = playerName,
        style = MaterialTheme.typography.headlineLarge,
        color = MHColor.negative,
        textAlign = TextAlign.Center
    )
    SpacerH(dp = 10.dp)
    Text(
        text = R.string.poor_end_description.str(),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
    SpacerH(dp = 10.dp)
    MHButton(stringRes = R.string.kill_player) {
        onHide(DeathResult(killPlayer = true))
    }
    SpacerH(dp = 40.dp)
}