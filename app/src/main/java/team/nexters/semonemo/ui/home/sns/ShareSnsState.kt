package team.nexters.semonemo.ui.home.sns

import team.nexters.domain.hold.model.NewHold


data class ShareSnsState(
    val newHolds: List<NewHold> = emptyList(),
    val loading: Boolean = true
)