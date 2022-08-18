package team.nexters.semonemo.ui.home.moimlist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.FetchMoimListUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.semonemo.ui.home.moimcreate.MoimCreateEvent
import team.nexters.semonemo.ui.home.moimdetail.MoimDetailState
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoimListViewModel @Inject constructor(
    private val fetchMoimListUseCase: FetchMoimListUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<MoimListState>(MoimListState.Empty)
    val uiState: StateFlow<MoimListState>
        get() = _uiState

    private val _eventFlow = MutableSharedFlow<MoimListEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun postEvent(event: MoimListEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    suspend fun fetchMoimList() = viewModelScope.launch {
        when (val result = fetchMoimListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                _uiState.value = MoimListState.Success(result.value)
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