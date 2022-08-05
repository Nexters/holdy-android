package team.nexters.semonemo.ui.splash

sealed class Navigation

object OnBoarding : Navigation()
data class Home(val tab: Int = 0) : Navigation() // TODO tab이 Int인건 추구 변경해야할듯
data class Board(val id: Long) : Navigation()

