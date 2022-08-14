package team.nexters.domain.user.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.user.repository.UserRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class GetUidUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Int>(dispatcher) {
    override suspend fun execute(param: Unit): ResultWrapper<Int> {
        return repository.getUid()
    }
}