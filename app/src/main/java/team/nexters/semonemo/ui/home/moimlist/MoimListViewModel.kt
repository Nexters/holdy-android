package team.nexters.semonemo.ui.home.moimlist

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.FetchMoimListUseCase
import team.nexters.semonemo.base.ExceptionEmitter
import javax.inject.Inject

@HiltViewModel
class MoimListViewModel @Inject constructor(
    private val fetchMoimListUseCase: FetchMoimListUseCase
) : ExceptionEmitter() {

    suspend fun fetchMoimList() = viewModelScope.launch {
        fetchMoimListUseCase()
            .onSuccess { moimList ->
                Log.d("FetchMoimList","Success: ${moimList[0].id}")
            }.onFailure { throwable->
                Log.d("FetchMoimList", "Fail: ${throwable.localizedMessage}")
                emitException(throwable)
            }
    }

}