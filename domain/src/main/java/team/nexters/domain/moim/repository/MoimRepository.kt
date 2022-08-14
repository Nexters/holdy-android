package team.nexters.domain.moim.repository

import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase
import team.nexters.domain.moim.usecase.PutAttendanceUseCase
import team.nexters.shared.ResultWrapper

interface MoimRepository {
    suspend fun fetchMoimList(): ResultWrapper<List<MoimModel>>
    suspend fun createMoim(param: CreateMoimUseCase.Param): ResultWrapper<MoimResponseModel>
    suspend fun getMoimDetail(param: GetMoimDetailUseCase.Param): ResultWrapper<MoimModel>
    suspend fun putAttendance(param: PutAttendanceUseCase.Param): ResultWrapper<MoimResponseModel>
}
