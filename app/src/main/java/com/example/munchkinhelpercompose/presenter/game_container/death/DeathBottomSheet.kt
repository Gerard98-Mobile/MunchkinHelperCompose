package com.example.munchkinhelpercompose.presenter.game_container.death

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeathBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onHide: () -> Unit,
) = ModalBottomSheet(
    onDismissRequest = { onHide() },
    sheetState = sheetState,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Content()
    }
}


@Composable
private fun Content() {

}