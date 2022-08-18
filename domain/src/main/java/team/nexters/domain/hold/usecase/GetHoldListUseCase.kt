package team.nexters.domain.hold.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.hold.model.Hold
import team.nexters.domain.hold.repository.HoldRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class GetHoldListUseCase @Inject constructor(
    private val repository: HoldRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Hold>>(dispatcher) {
    override suspend fun execute(param: Unit): ResultWrapper<List<Hold>> =
        repository.getHoldList()
}