package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.model.Game
import javax.inject.Inject

class UpdateGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(game: Game) {
        repository.update(game)
    }
}