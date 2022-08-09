package team.nexters.domain.moim.repository

import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase

interface MoimRepository {
    suspend fun fetchMoimList(): List<MoimModel>
    suspend fun createMoim(param: CreateMoimUseCase.Param): MoimResponseModel
}
