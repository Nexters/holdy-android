package team.nexters.semonemo.ui.home.hold

import team.nexters.semonemo.ui.home.moimlist.MoimListEvent

sealed class HoldEvent {
    object NavigateToHold : HoldEvent()
}