package com.example.munchkinhelpercompose.model

import com.example.munchkinhelpercompose.ui.theme.AppThemeType

data class Settings(
    val sound: Boolean = true,
    val appThemeType: AppThemeType = AppThemeType.AUTO
)