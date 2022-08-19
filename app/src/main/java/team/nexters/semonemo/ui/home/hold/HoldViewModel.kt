package team.nexters.semonemo.ui.home.hold

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.hold.usecase.GetHoldListUseCase
import team.nexters.domain.hold.usecase.GetNewHoldListUseCase
import team.nexters.domain.user.usecase.GetMyInfoUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HoldViewModel @Inject constructor(
    private val getHoldListUseCase: GetHoldListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val newHoldListUseCase: GetNewHoldListUseCase
) : BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<HoldEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _uiState = MutableStateFlow(HoldState())
    val uiState: StateFlow<HoldState>
        get() = _uiState

    fun init() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true)
        val deferredHold = async { getHoldList() }
        val deferredMyInfo = async { getMyInfo() }
        deferredHold.await()
        deferredMyInfo.await()
        _uiState.value = _uiState.value.copy(loading = false)
    }

    private suspend fun getHoldList(skipLoading: Boolean = false) = viewModelScope.launch {
        if (skipLoading.not()) {
            _uiState.value = _uiState.value.copy(loading = true)
        }
        when (val result = getHoldListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                Timber.e("홀드성공")
                if (skipLoading.not()) {
                    _uiState.value = _uiState.value.copy(
                        holds = result.value,
                        loading = false
                    )
                }
            }
            is ResultWrapper.Error -> {
                Timber.e("홀드실패")
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                result.e.printStackTrace()
                result.e.message?.let { emitException(it) }
            }
        }
    }

    private suspend fun getMyInfo(skipLoading: Boolean = false) = viewModelScope.launch {
        if (skipLoading.not()) {
            _uiState.value = _uiState.value.copy(loading = true)
        }
        when (val result = getMyInfoUseCase(Unit)) {
            is ResultWrapper.Success -> {
                if (skipLoading.not()) {
                    Timber.e("인포성공")
                    _uiState.value = _uiState.value.copy(
                        myInfo = result.value,
                        loading = false
                    )
                }
            }
            is ResultWrapper.Error -> {
                Timber.e("인포실패")
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                result.e.printStackTrace()
                result.e.message?.let { emitException(it) }
            }
        }
    }

    fun checkNewHold() = viewModelScope.launch(Dispatchers.IO) {
        delay(2000L)
        when (val result = newHoldListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                if (result.value.isNotEmpty()) {
                    _eventFlow.emit(HoldEvent.NavigateToHold)
                }
            }
            is ResultWrapper.Error -> {
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                result.e.message?.let { emitException(it) }
            }
        }
    }
}