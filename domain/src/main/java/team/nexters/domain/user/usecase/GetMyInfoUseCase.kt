package team.nexters.domain.user.usecase

import kotlinx.coroutines.CoroutineDispatcher
import team.nexters.domain.CoroutineUseCase
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.repository.UserRepository
import team.nexters.shared.ResultWrapper
import team.nexters.shared.di.IoDispatcher
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, LoginModel>(dispatcher) {

    override suspend fun execute(param: Unit): ResultWrapper<LoginModel> =
        repository.getMyInfo()

}
