package team.nexters.semonemo.ui.home.moimdetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase
import team.nexters.semonemo.base.BaseViewModel
import team.nexters.shared.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class MoimDetailViewModel @Inject constructor(
    private val moimDetailUseCase: GetMoimDetailUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<MoimDetailState>(MoimDetailState.Empty)
    val uiState: StateFlow<MoimDetailState>
        get() = _uiState

    fun getMoimDetail(id: Int) {
        viewModelScope.launch {
            when (val result = moimDetailUseCase(GetMoimDetailUseCase.Param(id))) {
                is ResultWrapper.Success ->{
                    _uiState.value = MoimDetailState.Success(result.value)
                }
                is ResultWrapper.Error -> {
                    emitException(result.message)
                }
                is ResultWrapper.Exception ->{
                    result.e.message?.let { emitException(it) }
                }
            }
        }
    }
}