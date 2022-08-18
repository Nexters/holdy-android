package team.nexters.semonemo.ui.home.hold

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import team.nexters.domain.hold.usecase.GetHoldListUseCase
import team.nexters.domain.user.usecase.GetMyInfoUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HoldViewModel @Inject constructor(
    private val getHoldListUseCase: GetHoldListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HoldState())
    val uiState: StateFlow<HoldState>
        get() = _uiState

    fun init() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true)
        val deferred1 = async { getHoldList() }
        val deferred2 = async { getMyInfo() }
        deferred1.await()
        deferred2.await()
        _uiState.value = _uiState.value.copy(loading = false)
    }

    suspend fun getHoldList(skipLoading: Boolean = false) = viewModelScope.launch {
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

    suspend fun getMyInfo(skipLoading: Boolean = false) = viewModelScope.launch {
        if (skipLoading.not()) {
            _uiState.value = _uiState.value.copy(loading = true)
        }
        when (val result = getMyInfoUseCase(Unit)) {
            is ResultWrapper.Success -> {
                if (skipLoading.not()) {
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
}