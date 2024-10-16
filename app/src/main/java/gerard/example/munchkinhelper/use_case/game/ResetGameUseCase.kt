package gerard.example.munchkinhelper.use_case.game

import gerard.example.munchkinhelper.data.GameRepository
import javax.inject.Inject

class ResetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() {
        repository.reset()
    }
}