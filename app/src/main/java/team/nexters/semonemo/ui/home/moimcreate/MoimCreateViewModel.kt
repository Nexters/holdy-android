package team.nexters.semonemo.ui.home.moimcreate

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class MoimCreateViewModel @Inject constructor(
    private val createMoimUseCase: CreateMoimUseCase
) : BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<MoimCreateEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun postEvent(event: MoimCreateEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun onCreateButtonClicked(
        startDate: String,
        endDate: String,
        summary: String,
        address: String,
        mapLink: String

    ) {
        viewModelScope.launch {
            when (val result = createMoimUseCase(
                CreateMoimUseCase.Param(
                    startDate,
                    endDate,
                    place = CreateMoimUseCase.Place(summary, address, mapLink)
                )
            )) {
                is ResultWrapper.Success -> {
                    _eventFlow.emit(MoimCreateEvent.NavigateToMoimList)
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
}