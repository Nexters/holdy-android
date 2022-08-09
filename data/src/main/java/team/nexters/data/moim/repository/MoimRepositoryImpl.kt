package team.nexters.data.moim.repository

import team.nexters.data.extension.apiCall
import team.nexters.data.moim.api.MoimApi
import team.nexters.data.moim.mapper.toData
import team.nexters.data.moim.mapper.toDomain
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import javax.inject.Inject

class MoimRepositoryImpl @Inject constructor(
    private val moimApi: MoimApi,
) : MoimRepository {
    override suspend fun fetchMoimList(): List<MoimModel> =
        moimApi.fetchMoimList().apiCall().map {
            it.toDomain()
        }.toList()

    override suspend fun createMoim(moimCreateModel: CreateMoimUseCase.Param): MoimResponseModel =
        moimApi.createMoim(moimCreateModel.toData()).apiCall().toDomain()

}
