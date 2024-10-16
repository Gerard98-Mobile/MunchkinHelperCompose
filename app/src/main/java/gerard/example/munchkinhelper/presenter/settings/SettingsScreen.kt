package gerard.example.munchkinhelper.presenter.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.navigation.AppScreen
import gerard.example.munchkinhelper.presenter.settings.components.BooleanSetting
import gerard.example.munchkinhelper.presenter.settings.components.SettingValue
import gerard.example.munchkinhelper.presenter.settings.components.default_ui_setting.DefaultUiSetting
import gerard.example.munchkinhelper.ui.components.MHToolbar
import gerard.example.munchkinhelper.ui.components.MHToolbarNavigationIcon
import gerard.example.munchkinhelper.ui.components.SpacerH
import gerard.example.munchkinhelper.ui.components.buttons.MHButton
import gerard.example.munchkinhelper.ui.theme.AppThemeType

private data class SettingsButton(
    @StringRes val titleRes: Int,
    val onClick: () -> Unit
)

@Composable
fun SettingsScreen(
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
                SettingsContent(navController)
            }
        }
    )
}

@Composable
private fun SettingsContent(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) = Column(Modifier.padding(10.dp)) {
    val state = viewModel.state.collectAsState().value

    val settings = listOf(
        BooleanSetting.create(
            title = R.string.sound,
            value = state.settings.sound,
            onDismiss = { viewModel.changeVisibleBottomSheet(null) }
        ) { result ->
            viewModel.updateSettings {
                it.copy(
                    sound = result
                )
            }
        },
        DefaultUiSetting(
            title = R.string.dark_mode,
            value = state.settings.appThemeType,
            onDismiss = { viewModel.changeVisibleBottomSheet(null) },
            possibleValues = AppThemeType.values().map { SettingValue(it, it.stringRepresentationRes) }
        ) { result ->
            viewModel.updateSettings {
                it.copy(
                    appThemeType = result
                )
            }
        }
    )

    val resetGameBottomSheet = SettingsBottomSheets.ResetGame(
        onDismiss = { viewModel.changeVisibleBottomSheet(null) }
    ) {
        viewModel.resetGame()
    }

    val buttons = listOf(
        SettingsButton(R.string.edit_game) {
            AppScreen.EditGame.navigate(navController)
        },
        SettingsButton(R.string.reset_game) {
            viewModel.changeVisibleBottomSheet(resetGameBottomSheet)
        },
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        items(settings) { item ->
            item.rowUi {
                viewModel.changeVisibleBottomSheet(SettingsBottomSheets.Setting(item))
            }
        }
    }

    buttons.forEach {
        MHButton(
            stringRes = it.titleRes,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = it.onClick
        )
        SpacerH(dp = 5.dp)
    }

    state.bottomSheet?.let {
        state.bottomSheet.content()
    }
}