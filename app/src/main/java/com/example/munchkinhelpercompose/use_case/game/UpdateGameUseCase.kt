package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.data.StoreGameRepository
import com.example.munchkinhelpercompose.model.Game

class UpdateGameUseCase(
    private val repository: GameRepository = StoreGameRepository()
) {
    suspend operator fun invoke(game: Game) {
        repository.update(game)
    }
}