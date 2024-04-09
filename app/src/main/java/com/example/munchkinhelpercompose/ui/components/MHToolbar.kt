package com.example.munchkinhelpercompose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.munchkinhelpercompose.ui.Back

enum class MhToolbarNavigationIcon(
    val content: @Composable (NavController) -> Unit
) {
    NONE({}),
    BACK({
        Back { it.popBackStack() }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MHToolbar(
    navController: NavController,
    @StringRes titleStringRes: Int,
    navigationIcon: MhToolbarNavigationIcon,
    actions: @Composable RowScope.() -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = titleStringRes)
            )
        },
        navigationIcon = {
            navigationIcon.content(navController)
        },
        actions = actions
    )
}