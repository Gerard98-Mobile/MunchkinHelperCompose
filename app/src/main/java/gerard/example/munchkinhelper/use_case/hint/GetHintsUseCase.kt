package gerard.example.munchkinhelper.use_case.hint

import gerard.example.munchkinhelper.data.HintRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHintsUseCase @Inject constructor(
    private val repository: HintRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.get()
    }
}