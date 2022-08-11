package team.nexters.domain.moim.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class FetchMoimListUseCase @Inject constructor(
    private val repository: MoimRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<MoimModel>>(dispatcher) {

    override suspend fun execute(param: Unit): ResultWrapper<List<MoimModel>> =
        repository.fetchMoimList()

}
