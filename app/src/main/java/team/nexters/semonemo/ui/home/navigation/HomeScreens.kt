package team.nexters.semonemo.ui.home.navigation

sealed class HomeScreens(
    val route: String,
) {
    object List : HomeScreens("LIST")
    object Creating : HomeScreens("CREATING")
    object Reward : HomeScreens("REWARD")
    object Hold: HomeScreens("HOLD")
}
