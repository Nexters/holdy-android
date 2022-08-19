package team.nexters.semonemo.ui.start

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.semonemo.ui.home.moimlist.MoimListState
import team.nexters.semonemo.ui.start.login.LoginState
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<StartEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _loginUiState = MutableStateFlow(LoginState())
    val loginUiState: StateFlow<LoginState>
        get() = _loginUiState

    val code = mutableStateOf("")

    fun login(code: String) {
        launchWithLoading {
            when (val result = loginUseCase(LoginUseCase.Param(code))) {
                is ResultWrapper.Success -> {
                    _eventFlow.emit(StartEvent.LoginSuccess)
                }
                is ResultWrapper.Error -> {
                    Timber.e("실패")
                    emitException(result.message)
                }
                is ResultWrapper.Exception -> {
                    Timber.e(result.e.message)
                    _loginUiState.value = _loginUiState.value.copy(isNetworkError = true)
                }
            }
        }
    }

    fun onStartButtonClicked() {
        viewModelScope.launch {
            _eventFlow.emit(StartEvent.OnBoardingFinished)
        }
    }

    fun refresh() {
        _loginUiState.value = _loginUiState.value.copy(isNetworkError = false)
    }
}