package com.example.munchkinhelpercompose.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


@Composable
fun Int.str() = stringResource(id = this)

@Composable
fun Int.str(vararg formatArgs: Any) = stringResource(id = this, formatArgs = formatArgs)

inline fun <T: Collection<*>> T.ifNotEmpty(func: (T) -> Unit) {
    if (this.isNotEmpty()) {
        func(this)
    }
}