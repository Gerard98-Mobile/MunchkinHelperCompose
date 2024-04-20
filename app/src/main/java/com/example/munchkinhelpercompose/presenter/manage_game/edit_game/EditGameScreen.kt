package com.example.munchkinhelpercompose.presenter.manage_game.edit_game

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.manage_game.PlayerRow
import com.example.munchkinhelpercompose.presenter.manage_game.edit_game.bottom_sheet.EditGameBottomSheet
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MHToolbarNavigationIcon
import com.example.munchkinhelpercompose.ui.components.buttons.MHTextButton

@Composable
fun EditGameScreen(
    navController: NavController,
    viewModel: EditGameViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            MHToolbar(
                titleStringRes = R.string.edit_game,
                navigationIcon = MHToolbarNavigationIcon.Back {
                    navController.popBackStack()
                },
                actions = {
                    MHTextButton(stringRes = R.string.save) {
                        viewModel.saveChanges()
                        navController.popBackStack()
                    }
                }
            )
        },
        content = {
            Column(Modifier.padding(it)) {
                EditGameContent(viewModel)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ColumnScope.EditGameContent(
    viewModel: EditGameViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LazyColumn(
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(bottom = 40.dp),
    ) {
        items(
            items = state.game?.players?.toList() ?: emptyList(),
            key = { player -> player.name }
        ) { player ->
            PlayerRow(
                name = player.name,
                modifier = Modifier
                    .clickable {
                        viewModel.changeVisibleBottomSheet(EditGameBottomSheet.EditPlayerName(player))
                    }
                    .animateItemPlacement(),
                onRemoveClick = {
                    viewModel.changeVisibleBottomSheet(EditGameBottomSheet.RemovePlayer(player))
                }
            )
        }
    }

    state.visibleBottomSheets?.let {
        it.content(viewModel)
    }

}


@Preview
@Composable
private fun EditGameContentPreview() {
    Column {
        EditGameContent()
    }
}