package gerard.example.munchkinhelper.use_case.game

import gerard.example.munchkinhelper.data.GameRepository
import javax.inject.Inject

class CreateGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(players: List<String>, safeLevels: Int) {
        repository.create(players, safeLevels)
    }
}