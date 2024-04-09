package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.data.StoreGameRepository
import com.example.munchkinhelpercompose.model.Game
import kotlinx.coroutines.flow.Flow

class GetGameUseCase(
    private val repository: GameRepository = StoreGameRepository()
) {
    suspend operator fun invoke() : Flow<Game?> = repository.get()
}