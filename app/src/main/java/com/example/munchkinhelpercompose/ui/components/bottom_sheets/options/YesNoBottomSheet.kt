package com.example.munchkinhelpercompose.ui.components.bottom_sheets.options

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R

@Composable
fun YesNoBottomSheet(
    @StringRes title: Int,
    onDismiss: () -> Unit,
    onResult: (Boolean) -> Unit
) = OptionsBottomSheet(
    title = title,
    options = listOf(
        Option(
            valueStringRes = R.string.yes,
            onSelect = { onResult(true) }
        ),
        Option(
            valueStringRes = R.string.no,
            onSelect = { onResult(false) }
        )
    ),
    onDismiss = onDismiss
)
