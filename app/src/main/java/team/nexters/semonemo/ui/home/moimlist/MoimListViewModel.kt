package team.nexters.semonemo.ui.home.moimlist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.FetchMoimListUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoimListViewModel @Inject constructor(
    private val fetchMoimListUseCase: FetchMoimListUseCase
) : BaseViewModel() {

    suspend fun fetchMoimList() = viewModelScope.launch {
        when (val result = fetchMoimListUseCase(Unit)) {
            is ResultWrapper.Success -> {
                Timber.tag("FetchMoimList").d("Success: " + result.value[0].id)
            }
            is ResultWrapper.Error -> {
                emitException(result.message)
            }
        }
    }

}