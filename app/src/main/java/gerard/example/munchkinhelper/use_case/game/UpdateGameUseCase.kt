package gerard.example.munchkinhelper.use_case.game

import gerard.example.munchkinhelper.data.GameRepository
import gerard.example.munchkinhelper.model.Game
import javax.inject.Inject

class UpdateGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(game: Game) {
        repository.update(game)
    }
}