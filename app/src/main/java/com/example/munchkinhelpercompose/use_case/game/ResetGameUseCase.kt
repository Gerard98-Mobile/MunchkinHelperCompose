package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.data.GameRepository
import javax.inject.Inject

class ResetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() {
        repository.reset()
    }
}