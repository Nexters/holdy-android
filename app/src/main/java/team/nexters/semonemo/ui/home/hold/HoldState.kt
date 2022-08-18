package team.nexters.semonemo.ui.home.hold

import team.nexters.domain.hold.model.Hold
import team.nexters.domain.user.model.LoginModel

data class HoldState(
    val myInfo: LoginModel? = null,
    val holds: List<Hold> = emptyList(),
    val loading: Boolean = true
)
