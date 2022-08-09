package team.nexters.domain.moim.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class CreateMoimUseCase @Inject constructor(
    private val repository: MoimRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<CreateMoimUseCase.Param, MoimResponseModel>(dispatcher) {

    override suspend fun execute(param: Param): MoimResponseModel = repository.createMoim(param)

    data class Param(
        val startDate: String,
        val endDate: String,
        val place: Place
    )

    data class Place(
        val summary: String,
        val address: String,
        val mapLink: String
    )
}
