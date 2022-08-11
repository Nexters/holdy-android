package team.nexters.domain.moim.repository

import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.shared.ResultWrapper

interface MoimRepository {
    suspend fun fetchMoimList(): ResultWrapper<List<MoimModel>>
    suspend fun createMoim(param: CreateMoimUseCase.Param): ResultWrapper<MoimResponseModel>
}
