package team.nexters.domain.user.repository

import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.model.LoginRequestModel

interface UserRepository {
    suspend fun login(loginRequestModel: LoginRequestModel): LoginModel
}