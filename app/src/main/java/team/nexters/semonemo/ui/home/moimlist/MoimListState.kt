package team.nexters.semonemo.ui.home.moimlist

import team.nexters.domain.moim.model.MoimModel


sealed class MoimListState {
    object Empty : MoimListState()
    data class Success(val moims: List<MoimModel>) : MoimListState()
}