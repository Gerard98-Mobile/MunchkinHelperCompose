package gerard.example.munchkinhelper.model

import gerard.example.munchkinhelper.ui.theme.AppThemeType

data class Settings(
    val sound: Boolean = true,
    val appThemeType: AppThemeType = AppThemeType.AUTO
)