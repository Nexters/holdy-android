package team.nexters.semonemo.ui.home.moimlist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.hold.usecase.GetNewHoldListUseCase
import team.nexters.domain.moim.usecase.FetchMoimListUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class MoimListViewModel @Inject constructor(
    private val fetchMoimListUseCase: FetchMoimListUseCase,
    private val newHoldListUseCase: GetNewHoldListUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MoimListState())
    val uiState: StateFlow<MoimListState>
        get() = _uiState

    private val _eventFlow = MutableSharedFlow<MoimListEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun postEvent(event: MoimListEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun fetchMoimList() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)
        when (val result = fetchMoimListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    moims = result.value
                )
            }
            is ResultWrapper.Error -> {
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                _uiState.value = _uiState.value.copy(isNetworkError = true)
                result.e.message?.let { emitException(it) }
            }
        }
    }

    fun checkNewHold() = viewModelScope.launch(Dispatchers.IO) {
        delay(2000L)
        when (val result = newHoldListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                if (result.value.isNotEmpty()) {
                    postEvent(MoimListEvent.NavigateToShareSns)
                }
            }
            is ResultWrapper.Error -> {
                emitException(result.message)
            }
            is ResultWrapper.Exception -> {
                _uiState.value = _uiState.value.copy(isNetworkError = true)
                result.e.message?.let { emitException(it) }
            }
        }
    }

    fun refresh() {
        _uiState.value = _uiState.value.copy(isNetworkError = false)
        checkNewHold()
        fetchMoimList()
    }

}