package com.example.munchkinhelpercompose.presenter.settings.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

abstract class UiSetting <T> {

    @get:StringRes abstract val title: Int?
    abstract val onResult: (T) -> Unit
    abstract val onDismiss: () -> Unit

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