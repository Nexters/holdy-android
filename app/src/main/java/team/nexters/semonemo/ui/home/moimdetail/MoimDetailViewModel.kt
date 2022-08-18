package team.nexters.semonemo.ui.home.moimdetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase
import team.nexters.domain.moim.usecase.PutAttendanceUseCase
import team.nexters.domain.moim.usecase.PutHostAttendanceUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoimDetailViewModel @Inject constructor(
    private val moimDetailUseCase: GetMoimDetailUseCase,
    private val putAttendanceUseCase: PutAttendanceUseCase,
    private val putHostAttendanceUseCase: PutHostAttendanceUseCase
) : BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<MoimDetailEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<MoimDetailState>(MoimDetailState.Empty)
    val uiState: StateFlow<MoimDetailState>
        get() = _uiState

    fun postEvent(event: MoimDetailEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun getMoimDetail(id: Int) {
        viewModelScope.launch {
            when (val result = moimDetailUseCase(GetMoimDetailUseCase.Param(id))) {
                is ResultWrapper.Success -> {
                    _uiState.value = MoimDetailState.Success(result.value)
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

    fun onAttendanceButtonClicked(moimId: Int, isCome: Boolean) {
        viewModelScope.launch {
            when (val result = putAttendanceUseCase(PutAttendanceUseCase.Param(moimId, isCome))) {
                is ResultWrapper.Success -> {
                    Timber.e("갈게요 성공")
                }
                is ResultWrapper.Error -> {
                    Timber.e("갈게요 실패${result.message}")
                }
                else -> {

                }
            }
        }
    }

    fun onCameButtonClicked(moimId: Int, userId: Int, isCome: Boolean) {
        viewModelScope.launch {
            when (val result =
                putHostAttendanceUseCase(PutHostAttendanceUseCase.Param(moimId, userId, isCome))) {
                is ResultWrapper.Success -> {
                    Timber.e("왔어요 성공")
                }
                is ResultWrapper.Error -> {
                    Timber.e("왔어요 실패${result.message}")
                }
                else -> {

                }
            }
        }
    }

}