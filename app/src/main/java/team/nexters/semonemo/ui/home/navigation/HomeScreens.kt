package team.nexters.semonemo.ui.home.navigation

import team.nexters.semonemo.R

sealed class HomeScreens(
    val route: String,
    val title: Int,
    val icon: Int
) {
    object List : HomeScreens("LIST", R.string.description_home, R.drawable.ic_launcher_background)
    object Creating : HomeScreens("CREATING", R.string.description_creating, R.drawable.ic_launcher_background)
    object Reward : HomeScreens("REWARD", R.string.description_reward, R.drawable.ic_launcher_background)
}
