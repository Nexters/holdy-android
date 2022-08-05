package team.nexters.data.moim.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import team.nexters.data.di.IoDispatcher
import team.nexters.data.extension.apiCall
import team.nexters.data.moim.api.MoimApi
import team.nexters.data.moim.mapper.toData
import team.nexters.data.moim.mapper.toDomain
import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import javax.inject.Inject

class MoimRepositoryImpl @Inject constructor(
    private val moimApi: MoimApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MoimRepository {
    override suspend fun fetchMoimList(): List<MoimModel> =
        withContext(dispatcher) {
            moimApi.fetchMoimList().apiCall().map {
                it.toDomain()
            }.toList()
        }

    override suspend fun createMoim(moimCreateModel: MoimCreateModel): MoimResponseModel =
        withContext(dispatcher) {
            moimApi.createMoim(moimCreateModel.toData()).apiCall().toDomain()
        }

}
