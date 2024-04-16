package com.example.munchkinhelpercompose.ui.components.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

abstract class UiSetting <T> {

    abstract val onResult: (T) -> Unit

    @Composable
    abstract fun rowUi(onClick: () -> Unit)

    @Composable
    abstract fun selectUi()

    abstract val value: T

    abstract val possibleValues: List<SettingValue<T>>

}

data class SettingValue <T> (
    val value: T,
    @StringRes val textRepresentationRes: Int
)