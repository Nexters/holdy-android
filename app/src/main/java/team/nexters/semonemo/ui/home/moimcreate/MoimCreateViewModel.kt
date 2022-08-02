package team.nexters.semonemo.ui.home.moimcreate

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.semonemo.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MoimCreateViewModel @Inject constructor(
    private val createMoimUseCase: CreateMoimUseCase
) : BaseViewModel(){

    private val _eventFlow = MutableSharedFlow<MoimCreateEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

}