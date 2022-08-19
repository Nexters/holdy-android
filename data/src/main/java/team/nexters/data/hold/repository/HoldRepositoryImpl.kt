package team.nexters.data.hold.repository

import team.nexters.data.hold.api.HoldApi
import team.nexters.data.hold.mapper.toDomain
import team.nexters.data.util.ResponseHandler
import team.nexters.domain.hold.model.Hold
import team.nexters.domain.hold.model.NewHold
import team.nexters.domain.hold.repository.HoldRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.flatMap
import javax.inject.Inject

class HoldRepositoryImpl @Inject constructor(private val holdApi: HoldApi) : HoldRepository {
    override suspend fun getHoldList(): ResultWrapper<List<Hold>> =
        ResponseHandler.handleApi {
            holdApi.getStamps()
        }.flatMap { stampList ->
            stampList.map { stamp ->
                stamp.toDomain()
            }.toList()
        }

    override suspend fun getNewHoldList(): ResultWrapper<List<NewHold>> =
        ResponseHandler.handleApi {
            holdApi.getNewStamps()
        }.flatMap { stampList ->
            stampList.map { stamp ->
                stamp.toDomain()
            }
        }

    override suspend fun getRemovedNewHoldList(): ResultWrapper<List<NewHold>> =
        ResponseHandler.handleApi {
            holdApi.getRemovedNewStamps()
        }.flatMap { stampList ->
            stampList.map { stamp ->
                stamp.toDomain()
            }
        }
}