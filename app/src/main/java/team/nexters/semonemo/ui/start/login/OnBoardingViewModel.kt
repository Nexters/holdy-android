package team.nexters.semonemo.ui.start.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<OnBoardingEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(code: String) {
        launchWithLoading {
            when (val result = loginUseCase(LoginUseCase.Param(code))) {
                is ResultWrapper.Success -> {
                    Timber.e("성공")
                    _eventFlow.emit(OnBoardingEvent.Success)
                }
                is ResultWrapper.Error -> {
                    Timber.e("실패")
                    emitException(result.message)
                }
                is ResultWrapper.Exception->{
                    result.e.message?.let { emitException(it) }
                }
            }
        }
    }
}