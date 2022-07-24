package team.nexters.data.user.mapper

import team.nexters.data.user.model.request.LoginRequest
import team.nexters.data.user.model.response.LoginResponse
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.model.LoginRequestModel

internal fun LoginResponse.toDomain() = LoginModel(
    result = result,
    loginUser = loginUser?.toDomain(),
)

private fun LoginResponse.LoginUser.toDomain() = LoginModel.LoginUser(
    nickname = nickname
)

internal fun LoginRequestModel.toData() = LoginRequest(
    authKey = authKey
)