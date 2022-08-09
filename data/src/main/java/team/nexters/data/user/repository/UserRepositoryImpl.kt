package team.nexters.data.user.repository

import team.nexters.data.datastore.DataStoreManager
import team.nexters.data.extension.apiCall
import team.nexters.data.extension.headerIntercept
import team.nexters.data.user.api.UserApi
import team.nexters.data.user.mapper.toData
import team.nexters.data.user.mapper.toDomain
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.repository.UserRepository
import team.nexters.domain.user.usecase.LoginUseCase
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val dataStore: DataStoreManager
) : UserRepository {
    override suspend fun login(loginRequestModel: LoginUseCase.Param): LoginModel =
        userApi.login(loginRequestModel.toData())
            .headerIntercept(dataStore)
            .apiCall()
            .toDomain()

}
