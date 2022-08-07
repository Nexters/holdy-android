package team.nexters.domain.user.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.model.LoginRequestModel
import team.nexters.domain.user.repository.UserRepository
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<LoginRequestModel, LoginModel>(dispatcher) {

    override suspend fun execute(param: LoginRequestModel): LoginModel {
        return repository.login(param)
    }
}
