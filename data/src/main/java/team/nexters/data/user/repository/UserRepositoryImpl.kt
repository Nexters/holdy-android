package team.nexters.data.user.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import team.nexters.data.datastore.DataStoreManager
import team.nexters.shared.di.IoDispatcher
import team.nexters.data.extension.apiCall
import team.nexters.data.extension.headerIntercept
import team.nexters.data.user.api.UserApi
import team.nexters.data.user.mapper.toData
import team.nexters.data.user.mapper.toDomain
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.model.LoginRequestModel
import team.nexters.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val dataStore: DataStoreManager
) : UserRepository {
    override suspend fun login(loginRequestModel: LoginRequestModel): LoginModel =
        withContext(dispatcher) {
            userApi.login(loginRequestModel.toData())
                .headerIntercept(dataStore)
                .apiCall()
                .toDomain()
        }
}
