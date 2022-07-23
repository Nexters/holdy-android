package team.nexters.data.user.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import team.nexters.data.di.IoDispatcher
import team.nexters.data.extension.apiCall
import team.nexters.data.user.api.UserApi
import team.nexters.data.user.mapper.toDomain
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun login(authKey: String): LoginModel = withContext(dispatcher) {
        userApi.login(authKey).apiCall().toDomain()
    }

}