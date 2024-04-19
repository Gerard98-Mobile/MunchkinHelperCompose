package com.example.munchkinhelpercompose.presenter.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.presenter.settings.components.BooleanSetting
import com.example.munchkinhelpercompose.ui.components.MHToolbar
import com.example.munchkinhelpercompose.ui.components.MHToolbarNavigationIcon

@Composable
fun SettingsView(
    navController: NavController
) {
    Scaffold(
        topBar = {
            MHToolbar(
                titleStringRes = R.string.settings,
                navigationIcon = MHToolbarNavigationIcon.Back {
                    navController.popBackStack()
                },
            )
        },
        content = {
            Column(Modifier.padding(it)) {
                SettingsContent()
            }
        }
    )
}

@Composable
private fun ColumnScope.SettingsContent(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    val settings = listOf(
        BooleanSetting.create(
            title = R.string.sound,
            value = state.settings.sound,
            onDismiss = { viewModel.editSetting(null) }
        ) { result ->
            viewModel.updateSettings {
                it.copy(
                    sound = result
                )
            }
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        items(settings) { item ->
            item.rowUi {
                viewModel.editSetting(item)
            }
        }
    }
    state.editingSetting?.let {
        state.editingSetting.selectUi()
    }
}