package gerard.example.munchkinhelper.use_case.hint

import gerard.example.munchkinhelper.data.HintRepository
import javax.inject.Inject

class SaveHintsUseCase @Inject constructor(
    private val repository: HintRepository
) {
    suspend operator fun invoke(hints: List<String>) {
        return repository.save(hints)
    }
}