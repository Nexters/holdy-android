package team.nexters.semonemo.ui.home.navigation

sealed class HomeScreens(
    val route: String,
) {
    object List : HomeScreens("LIST")
    object Creating : HomeScreens("CREATING")
    object Detail : HomeScreens("DETAIL")
    object Hold: HomeScreens("HOLD")
    object ShareSNS: HomeScreens("SHARE_SNS")
}
