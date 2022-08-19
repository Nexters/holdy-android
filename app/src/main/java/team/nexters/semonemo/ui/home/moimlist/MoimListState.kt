package team.nexters.semonemo.ui.home.moimlist

import team.nexters.domain.moim.model.MoimModel

data class MoimListState(
    val moims: List<MoimModel> = emptyList(),
    val isLoading: Boolean = true,
    val isNetworkError: Boolean = false
)