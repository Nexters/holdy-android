package team.nexters.data.moim.repository

import team.nexters.data.moim.api.MoimApi
import team.nexters.data.moim.mapper.toData
import team.nexters.data.moim.mapper.toDomain
import team.nexters.data.util.ResponseHandler.handleApi
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.shared.ResultWrapper
import team.nexters.shared.flatMap
import javax.inject.Inject

class MoimRepositoryImpl @Inject constructor(
    private val moimApi: MoimApi,
) : MoimRepository {
    override suspend fun fetchMoimList(): ResultWrapper<List<MoimModel>> = handleApi {
        moimApi.fetchMoimList()
    }.flatMap {
        it.map { moimResponse ->
            moimResponse.toDomain()
        }.toList()
    }

    override suspend fun createMoim(moimCreateModel: CreateMoimUseCase.Param): ResultWrapper<MoimResponseModel> =
        handleApi {
            moimApi.createMoim(moimCreateModel.toData())
        }.flatMap { it.toDomain() }

}
