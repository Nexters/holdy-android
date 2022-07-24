package team.nexters.domain.user.usecase

import team.nexters.domain.user.model.LoginRequestModel
import team.nexters.domain.user.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(loginRequestModel: LoginRequestModel) = runCatching {
        repository.login(loginRequestModel)
    }
}
