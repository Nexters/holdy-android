package team.nexters.semonemo.ui.home.moimlist

import team.nexters.semonemo.ui.home.moimcreate.MoimCreateEvent

sealed class MoimListEvent {
    object NavigateToMoimCreate : MoimListEvent()
    object NavigateToHold : MoimListEvent()
    data class NavigateToMoimDetail(val id: Int) : MoimListEvent()
}