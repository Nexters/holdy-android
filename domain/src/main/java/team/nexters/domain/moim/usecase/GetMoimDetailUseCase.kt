package team.nexters.domain.moim.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.moim.model.MoimDetailModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class GetMoimDetailUseCase @Inject constructor(
    private val repository: MoimRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<GetMoimDetailUseCase.Param, MoimDetailModel>(dispatcher) {

    override suspend fun execute(param: Param): ResultWrapper<MoimDetailModel> =
        repository.getMoimDetail(param)


    data class Param(
        val id: Int
    )
}