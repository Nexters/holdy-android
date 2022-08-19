package team.nexters.semonemo.ui.home.sns

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.hold.usecase.GetNewHoldListUseCase
import team.nexters.domain.hold.usecase.GetRemovedNoewHoldListUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShareSnsViewModel @Inject constructor(
    private val getRemovedNoewHoldListUseCase: GetRemovedNoewHoldListUseCase
) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<ShareSnsEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _uiState = MutableStateFlow(ShareSnsState())
    val uiState: StateFlow<ShareSnsState>
        get() = _uiState


    fun postEvent(event: ShareSnsEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun getNewHoldList() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true)
        when (val result = getRemovedNoewHoldListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                Timber.e("새 홀드성공")
                _uiState.value = _uiState.value.copy(
                    newHolds = result.value,
                    loading = false
                )
            }
            is ResultWrapper.Error -> {
                Timber.e("새 홀드 실패")
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                result.e.printStackTrace()
                result.e.message?.let { emitException(it) }
            }
        }
    }
}