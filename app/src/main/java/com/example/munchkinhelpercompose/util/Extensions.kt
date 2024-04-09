package com.example.munchkinhelpercompose.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


@Composable
fun Int.str() = stringResource(id = this)

inline fun <T: Collection<*>> T.ifNotEmpty(func: (T) -> Unit) {
    if (this.isNotEmpty()) {
        func(this)
    }
}