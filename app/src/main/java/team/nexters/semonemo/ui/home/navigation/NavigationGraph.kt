package team.nexters.semonemo.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.nexters.semonemo.ui.home.hold.HoldScreen
import team.nexters.semonemo.ui.home.moimcreate.MoimCreateScreen
import team.nexters.semonemo.ui.home.moimdetail.MoimDetailScreen
import team.nexters.semonemo.ui.home.moimlist.MoimListScreen

@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreens.List.route,
    ) {
        composable(
            route = HomeScreens.List.route,
        ) {
            MoimListScreen(
                navigateToMoimCreate = {
                    navController.navigate(HomeScreens.Creating.route)
                },
                navigateToMoimDetail = {
                    navController.navigate(HomeScreens.Reward.route)
                },
                navigateToHold = {
                    navController.navigate(HomeScreens.Hold.route)
                }
            )
        }
        composable(
            route = HomeScreens.Creating.route,
        ) {
            MoimCreateScreen(onBackPressed = { navController.popBackStack() })
        }
        composable(HomeScreens.Reward.route) {
            MoimDetailScreen {
                navController.popBackStack()
            }
        }
        composable(HomeScreens.Hold.route) {
            HoldScreen {
                navController.popBackStack()
            }
        }
    }
}
