package team.nexters.domain.user.repository

import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.shared.ResultWrapper

interface UserRepository {
    suspend fun login(loginRequestModel: LoginUseCase.Param): ResultWrapper<LoginModel>
}