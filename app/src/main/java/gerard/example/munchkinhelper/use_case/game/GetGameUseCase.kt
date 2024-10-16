package gerard.example.munchkinhelper.use_case.game

import gerard.example.munchkinhelper.data.GameRepository
import gerard.example.munchkinhelper.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() : Flow<Game?> = repository.get()
}