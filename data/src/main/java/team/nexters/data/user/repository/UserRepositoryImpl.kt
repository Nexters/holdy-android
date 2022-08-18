package team.nexters.data.user.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import team.nexters.data.datastore.DataStoreManager
import team.nexters.data.user.api.UserApi
import team.nexters.data.user.mapper.toData
import team.nexters.data.user.mapper.toDomain
import team.nexters.data.util.ResponseHandler.handleApi
import team.nexters.data.util.ResponseHandler.headerIntercept
import team.nexters.data.util.ResponseHandler.idIntercept
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.repository.UserRepository
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.shared.ResultWrapper
import team.nexters.shared.flatMap
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val dataStore: DataStoreManager
) : UserRepository {
    override suspend fun login(loginRequestModel: LoginUseCase.Param) =
        handleApi {
            userApi.login(loginRequestModel.toData())
                .headerIntercept(dataStore)
                .idIntercept(dataStore)
        }.flatMap { it.toDomain() }

    override suspend fun getMyInfo(): ResultWrapper<LoginModel> =
        handleApi {
            userApi.getMyInfo()
        }.flatMap { it.toDomain() }

    override suspend fun getSession(): ResultWrapper<String> {
        val session = runBlocking {
            withContext(Dispatchers.IO) {
                runCatching {
                    dataStore.session.first()
                }
            }
        }
        session.onSuccess {
            return ResultWrapper.Success(it)
        }
        return ResultWrapper.Success("")
    }

    override suspend fun getUid(): ResultWrapper<Int> {
        val uid = runBlocking {
            withContext(Dispatchers.IO) {
                runCatching {
                    dataStore.uid.first()
                }
            }
        }
        uid.onSuccess {
            return ResultWrapper.Success(it)
        }
        return ResultWrapper.Success(0)
    }

}
