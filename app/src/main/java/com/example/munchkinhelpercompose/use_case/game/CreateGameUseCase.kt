package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.data.StoreGameRepository

class CreateGameUseCase(
    private val repository: GameRepository = StoreGameRepository()
) {
    suspend operator fun invoke(players: List<String>) {
        repository.create(players)
    }
}