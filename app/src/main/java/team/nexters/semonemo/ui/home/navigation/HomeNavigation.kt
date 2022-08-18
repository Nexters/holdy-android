package team.nexters.semonemo.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import team.nexters.semonemo.ui.home.hold.HoldScreen
import team.nexters.semonemo.ui.home.moimcreate.MoimCreateScreen
import team.nexters.semonemo.ui.home.moimdetail.MoimDetailScreen
import team.nexters.semonemo.ui.home.moimlist.MoimListScreen
import team.nexters.semonemo.ui.home.sns.ShareSnsScreen

@Composable
internal fun HomeNavigation(navController: NavHostController) {
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
                navigateToMoimDetail = { id ->
                    navController.navigate("${HomeScreens.Detail.route}/$id")
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
                onBackPressed = { navController.navigate(HomeScreens.List.route) }
            )
        }
        composable(
            route = HomeScreens.Detail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            MoimDetailScreen(
                onBackPressed = { navController.navigate(HomeScreens.List.route) },
                id = id
            )
        }
        composable(
            route = HomeScreens.Hold.route
        ) {
            HoldScreen(
                onBackPressed = { navController.navigate(HomeScreens.List.route) }
            )
        }
        composable(
            route = HomeScreens.ShareSNS.route
        ) {
            ShareSnsScreen(
                onBackPressed = { navController.navigate(HomeScreens.List.route) }
            )
        }
    }
}
