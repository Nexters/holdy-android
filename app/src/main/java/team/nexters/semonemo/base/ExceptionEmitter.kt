package team.nexters.semonemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class ExceptionEmitter : ViewModel() {
    private val _exceptionChannel = Channel<Throwable>()
    val exceptionFlow = _exceptionChannel.receiveAsFlow()

    open fun emitException(exception: Throwable) = viewModelScope.launch {
        _exceptionChannel.send(exception)
    }
}