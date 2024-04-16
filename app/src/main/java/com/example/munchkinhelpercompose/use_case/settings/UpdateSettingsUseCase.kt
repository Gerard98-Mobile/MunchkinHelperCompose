package com.example.munchkinhelpercompose.use_case.settings

import com.example.munchkinhelpercompose.data.SettingsRepository
import com.example.munchkinhelpercompose.model.Settings
import javax.inject.Inject

class UpdateSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(settings: Settings) = repository.update(settings)
}