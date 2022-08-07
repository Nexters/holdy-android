package team.nexters.semonemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _commonErrorChannel = Channel<String>()
    val commonErrorChannel = _commonErrorChannel.receiveAsFlow()

    protected fun emitException(message: String) = viewModelScope.launch {
        _commonErrorChannel.send(message)
    }

    private fun startLoading() {

    }

    private fun stopLoading() {

    }

    fun launchWithLoading(
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
        try {
            startLoading()
            block.invoke(this)
        } finally {
            stopLoading()
        }
    }

}