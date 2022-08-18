package team.nexters.domain.hold.repository

import team.nexters.domain.hold.model.Hold
import team.nexters.shared.ResultWrapper

interface HoldRepository {
    suspend fun getHoldList(): ResultWrapper<List<Hold>>
}