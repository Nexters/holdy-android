package team.nexters.domain.moim.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class PutHostAttendanceUseCase @Inject constructor(
    private val repository: MoimRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<PutHostAttendanceUseCase.Param, MoimResponseModel>(dispatcher) {

    override suspend fun execute(param: Param): ResultWrapper<MoimResponseModel> =
        repository.putHostAttendance(param)

    data class Param(
        val meetingId: Int,
        val userId: Int,
        val attend: Boolean
    )

}