package com.example.munchkinhelpercompose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.munchkinhelpercompose.ui.MHIcon

sealed class MHToolbarNavigationIcon(
    val ui: @Composable () -> Unit,
) {
    object None : MHToolbarNavigationIcon({})

    class Back(
        onClose: () -> Unit
    ) : MHToolbarNavigationIcon(
        ui = { MHIcon.Back { onClose.invoke() } }
    )

    class Close(
        onClose: () -> Unit,
    ) : MHToolbarNavigationIcon(
        ui = { MHIcon.Close { onClose.invoke() } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MHToolbar(
    @StringRes titleStringRes: Int,
    navigationIcon: MHToolbarNavigationIcon,
    actions: @Composable RowScope.() -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = titleStringRes)
            )
        },
        navigationIcon = {
            navigationIcon.ui.invoke()
        },
        actions = actions
    )
}