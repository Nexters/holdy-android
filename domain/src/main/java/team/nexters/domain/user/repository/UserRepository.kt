package team.nexters.domain.user.repository

import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.shared.ResultWrapper

interface UserRepository {
    suspend fun login(loginRequestModel: LoginUseCase.Param): ResultWrapper<LoginModel>
    suspend fun getMyInfo(): ResultWrapper<LoginModel>
    suspend fun getSession(): ResultWrapper<String>
    suspend fun getUid(): ResultWrapper<Int>
}