package team.nexters.semonemo.ui.start.login

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import team.nexters.semonemo.base.ExceptionEmitter

class LoginViewModel : ExceptionEmitter() {
    private val _eventFlow = MutableSharedFlow<LoginEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(code: String) {
        //TODO 로그인 시 Success, Failed 이벤트 방출
    }
}