package team.nexters.semonemo.ui.start.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.user.model.LoginRequestModel
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
        viewModelScope.launch {
            loginUseCase(LoginRequestModel(code)).onSuccess {
                if (it.loginUser != null) {
                    _eventFlow.emit(LoginEvent.Success)
                } else {
                    _eventFlow.emit(LoginEvent.Failed)
                }
            }.onFailure {
                it.printStackTrace()
                emitException(it)
            }
        }
    }
}