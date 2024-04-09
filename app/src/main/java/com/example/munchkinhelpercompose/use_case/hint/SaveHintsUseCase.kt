package com.example.munchkinhelpercompose.use_case.hint

import com.example.munchkinhelpercompose.data.HintRepository
import javax.inject.Inject

class SaveHintsUseCase @Inject constructor(
    private val repository: HintRepository
) {
    suspend operator fun invoke(hints: List<String>) {
        return repository.save(hints)
    }
}