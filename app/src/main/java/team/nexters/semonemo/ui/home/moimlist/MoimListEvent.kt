package team.nexters.semonemo.ui.home.moimlist

sealed class MoimListEvent {
    object NavigateToMoimCreate : MoimListEvent()
    object NavigateToHold : MoimListEvent()
    data class NavigateToMoimDetail(val id: Int) : MoimListEvent()
}