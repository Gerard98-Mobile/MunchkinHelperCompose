package com.example.munchkinhelpercompose.use_case.hint

import com.example.munchkinhelpercompose.data.HintRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHintsUseCase @Inject constructor(
    private val repository: HintRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.get()
    }
}