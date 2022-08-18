package team.nexters.semonemo.ui.start.navigation


sealed class StartScreens(
    val route: String,
) {
    object Login : StartScreens("LOGIN")
    object OnBoarding : StartScreens("ON_BOARDING")
}

