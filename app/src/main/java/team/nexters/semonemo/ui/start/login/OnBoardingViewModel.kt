package team.nexters.semonemo.ui.start.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.model.LoginRequestModel
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<OnBoardingEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(code: String) {
        launchWithLoading {
            when (val result = loginUseCase(LoginRequestModel(code))) {
                is ResultWrapper.Success -> {
                    if (emitErrorIfNeed(result.value))
                        return@launchWithLoading
                    _eventFlow.emit(OnBoardingEvent.Success)
                }
                is ResultWrapper.Error -> {
                    emitException(result.message)
                }
            }
        }
    }

    // 이걸 모든 응답마다 처리해야함...
    private fun emitErrorIfNeed(response: LoginModel): Boolean {
        if (response.result != "SUCCESS") {
            emitException(response.result)
            return true
        }
        return false
    }
}