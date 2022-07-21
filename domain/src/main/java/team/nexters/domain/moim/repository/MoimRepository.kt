package team.nexters.domain.moim.repository

import team.nexters.domain.moim.model.MoimModel

interface MoimRepository {
    suspend fun fetchMoimList(): List<MoimModel>
}