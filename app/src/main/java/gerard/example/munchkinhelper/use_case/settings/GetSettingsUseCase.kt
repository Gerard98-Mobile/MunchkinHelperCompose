package gerard.example.munchkinhelper.use_case.settings

import gerard.example.munchkinhelper.data.SettingsRepository
import gerard.example.munchkinhelper.model.Settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke() : Flow<Settings> = repository.get()
}