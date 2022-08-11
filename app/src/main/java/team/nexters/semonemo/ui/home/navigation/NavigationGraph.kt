package team.nexters.semonemo.ui.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import team.nexters.semonemo.ui.home.moimcreate.MoimCreateScreen
import team.nexters.semonemo.ui.home.moimdetail.MoimDetailScreen
import team.nexters.semonemo.ui.home.moimlist.MoimListScreen

@OptIn(ExperimentalAnimationApi::class)
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
    }
}