package team.nexters.domain.hold.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.hold.model.NewHold
import team.nexters.domain.hold.repository.HoldRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class GetNewHoldListUseCase @Inject constructor(
    private val repository: HoldRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<NewHold>>(dispatcher) {
    override suspend fun execute(param: Unit): ResultWrapper<List<NewHold>> =
        repository.getNewHoldList()
}