package com.example.munchkinhelpercompose.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun MHButton(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = stringResource(id = stringRes))
    }
}

@Composable
fun MHTextButton(@StringRes stringRes: Int, onClick: () -> Unit) {
    MHTextButton(string = stringResource(id = stringRes), onClick)
}

@Composable
fun MHTextButton(string: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = string)
    }
}