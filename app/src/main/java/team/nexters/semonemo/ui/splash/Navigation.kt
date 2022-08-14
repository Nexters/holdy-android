package team.nexters.semonemo.ui.splash

sealed class Navigation {
    object Login : Navigation()
    object Home : Navigation()
    data class MoimDetail(val id: Long) : Navigation()
}


