package team.nexters.data.moim.repository

import team.nexters.data.moim.api.MoimApi
import team.nexters.data.moim.mapper.toData
import team.nexters.data.moim.mapper.toDomain
import team.nexters.data.moim.model.request.Attendance
import team.nexters.data.moim.model.request.HostAttendance
import team.nexters.data.util.ResponseHandler.handleApi
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase
import team.nexters.domain.moim.usecase.PutAttendanceUseCase
import team.nexters.domain.moim.usecase.PutHostAttendanceUseCase
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

    override suspend fun createMoim(param: CreateMoimUseCase.Param): ResultWrapper<MoimResponseModel> =
        handleApi {
            moimApi.createMoim(param.toData())
        }.flatMap { it.toDomain() }

    override suspend fun getMoimDetail(param: GetMoimDetailUseCase.Param): ResultWrapper<MoimModel> =
        handleApi {
            moimApi.getMoimDetail(param.toData().id)
        }.flatMap { it.toDomain() }

    override suspend fun putAttendance(param: PutAttendanceUseCase.Param): ResultWrapper<MoimResponseModel> =
        handleApi {
            moimApi.putAttendance(param.id, Attendance(param.wantToAttend))
        }.flatMap { it.toDomain() }

    override suspend fun putHostAttendance(param: PutHostAttendanceUseCase.Param): ResultWrapper<MoimResponseModel> =
        handleApi {
            moimApi.putHostAttendance(param.meetingId, param.userId, HostAttendance(param.attend))
        }.flatMap { it.toDomain() }

}
