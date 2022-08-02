package team.nexters.domain.moim.repository

import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel

interface MoimRepository {
    suspend fun fetchMoimList(): List<MoimModel>
    suspend fun createMoim(moimCreateModel: MoimCreateModel): MoimResponseModel
}