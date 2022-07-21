package team.nexters.semonemo.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.nexters.semonemo.ui.home.moimlist.MoimListScreen

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreens.List.route
    ) {
        composable(HomeScreens.List.route) {
            MoimListScreen()
        }
        composable(HomeScreens.Creating.route) {

        }
        composable(HomeScreens.Reward.route) {
        }
    }
}