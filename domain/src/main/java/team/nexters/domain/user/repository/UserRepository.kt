package team.nexters.domain.user.repository

import team.nexters.domain.user.model.LoginModel

interface UserRepository {
    suspend fun login(authKey: String): LoginModel
}