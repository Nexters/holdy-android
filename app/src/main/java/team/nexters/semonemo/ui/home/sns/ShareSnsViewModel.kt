package team.nexters.semonemo.ui.home.sns

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.nexters.semonemo.base.BaseViewModel

class ShareSnsViewModel : BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<ShareSnsEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    fun postEvent(event: ShareSnsEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}