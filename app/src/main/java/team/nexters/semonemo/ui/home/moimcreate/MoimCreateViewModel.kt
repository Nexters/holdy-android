package team.nexters.semonemo.ui.home.moimcreate

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.semonemo.base.BaseViewModel
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

    fun onCreateButtonClicked(moimCreateModel: MoimCreateModel) {
        viewModelScope.launch {
            createMoimUseCase(moimCreateModel).onSuccess { response ->
                if (response.id == null) {
                    _eventFlow.emit(MoimCreateEvent.NavigateToMoimList)
                } else {
                    _eventFlow.emit(MoimCreateEvent.CreationFailed(response.result))
                }
            }.onFailure { throwable ->
                throwable.printStackTrace()
                emitException(throwable.localizedMessage)
            }
        }
    }
}