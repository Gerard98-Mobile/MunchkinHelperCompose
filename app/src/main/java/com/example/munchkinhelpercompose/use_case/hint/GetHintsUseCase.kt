package com.example.munchkinhelpercompose.use_case.hint

import com.example.munchkinhelpercompose.data.HintRepository
import com.example.munchkinhelpercompose.data.StoreHintRepository
import kotlinx.coroutines.flow.Flow

class GetHintsUseCase(
    private val repository: HintRepository = StoreHintRepository()
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.get()
    }
}