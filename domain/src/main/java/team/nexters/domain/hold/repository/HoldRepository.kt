package team.nexters.domain.hold.repository

import team.nexters.domain.hold.model.Hold
import team.nexters.domain.hold.model.NewHold
import team.nexters.shared.ResultWrapper

interface HoldRepository {
    suspend fun getHoldList(): ResultWrapper<List<Hold>>
    suspend fun getNewHoldList(): ResultWrapper<List<NewHold>>
    suspend fun getRemovedNewHoldList(): ResultWrapper<List<NewHold>>
}