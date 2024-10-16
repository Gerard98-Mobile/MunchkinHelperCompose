package gerard.example.munchkinhelper.use_case.settings

import gerard.example.munchkinhelper.data.SettingsRepository
import gerard.example.munchkinhelper.model.Settings
import javax.inject.Inject

class UpdateSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(settings: Settings) = repository.update(settings)
}