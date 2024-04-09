package com.example.munchkinhelpercompose.use_case.hint

import com.example.munchkinhelpercompose.data.HintRepository
import com.example.munchkinhelpercompose.data.StoreHintRepository

class SaveHintsUseCase(
    private val repository: HintRepository = StoreHintRepository()
) {
    suspend operator fun invoke(hints: List<String>) {
        return repository.save(hints)
    }
}