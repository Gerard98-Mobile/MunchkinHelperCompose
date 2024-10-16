package com.example.munchkinhelpercompose.use_case.settings

import com.example.munchkinhelpercompose.data.SettingsRepository
import com.example.munchkinhelpercompose.model.Settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke() : Flow<Settings> = repository.get()
}