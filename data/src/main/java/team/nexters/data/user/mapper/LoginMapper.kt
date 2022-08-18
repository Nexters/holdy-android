package team.nexters.data.user.mapper

import team.nexters.data.user.model.request.LoginRequest
import team.nexters.data.user.model.response.LoginResponse
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.usecase.LoginUseCase

internal fun LoginResponse.toDomain() = LoginModel(
    id = id,
    nickname = nickname,
    group = group,
    profileImageUrl = profileImageUrl
)

internal fun LoginUseCase.Param.toData() = LoginRequest(
    authKey = authKey
)