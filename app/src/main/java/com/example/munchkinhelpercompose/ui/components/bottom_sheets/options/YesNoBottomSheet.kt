package com.example.munchkinhelpercompose.ui.components.bottom_sheets.options

import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.util.str

@Composable
fun YesNoBottomSheet(
    title: String,
    onDismiss: () -> Unit,
    onResult: (Boolean) -> Unit
) = OptionsBottomSheet(
    title = title,
    options = listOf(
        Option(
            text = R.string.yes.str(),
            onSelect = { onResult(true) }
        ),
        Option(
            text = R.string.no.str(),
            onSelect = { onResult(false) }
        )
    ),
    onDismiss = onDismiss
)
