package team.nexters.semonemo.ui.home.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import team.nexters.semonemo.ui.home.hold.HoldScreen
import team.nexters.semonemo.ui.home.moimcreate.MoimCreateScreen
import team.nexters.semonemo.ui.home.moimdetail.MoimDetailScreen
import team.nexters.semonemo.ui.home.moimlist.MoimListScreen
import team.nexters.semonemo.ui.home.sns.ShareSnsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun NavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
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
            MoimCreateScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(HomeScreens.Reward.route) {
            MoimDetailScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(HomeScreens.Hold.route) {
            HoldScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(HomeScreens.ShareSNS.route) {
            ShareSnsScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
