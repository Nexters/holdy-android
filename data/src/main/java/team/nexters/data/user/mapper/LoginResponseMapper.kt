package team.nexters.data.user.mapper

import team.nexters.data.user.model.LoginResponse
import team.nexters.domain.user.model.LoginModel

internal fun LoginResponse.toDomain() = LoginModel(
    result = result,
    loginUser = loginUser?.toDomain()
)

private fun LoginResponse.LoginUser.toDomain() = LoginModel.LoginUser(
    nickname = nickname
)