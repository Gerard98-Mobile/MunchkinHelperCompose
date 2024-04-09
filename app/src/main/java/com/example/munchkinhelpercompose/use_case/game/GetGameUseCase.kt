package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import com.example.munchkinhelpercompose.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() : Flow<Game?> = repository.get()
}