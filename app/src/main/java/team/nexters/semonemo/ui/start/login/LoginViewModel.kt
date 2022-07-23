package team.nexters.semonemo.ui.start.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.semonemo.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<LoginEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(code: String) {
        //TODO 로그인 시 Success, Failed 이벤트 방출
    }
}