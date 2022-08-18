package team.nexters.semonemo.ui.start.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.nexters.semonemo.ui.start.login.LoginScreen
import team.nexters.semonemo.ui.start.StartViewModel
import team.nexters.semonemo.ui.start.onboarding.OnBoardingScreen

@Composable
internal fun StartNavigation(
    viewModel: StartViewModel = hiltViewModel(),
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = StartScreens.Login.route,
    ) {
        composable(
            route = StartScreens.Login.route,
        ) {
            LoginScreen(
                viewModel = viewModel,
                navigateToOnBoarding = { navController.navigate(StartScreens.OnBoarding.route) }
            )
        }
        composable(
            route = StartScreens.OnBoarding.route,
        ) {
            OnBoardingScreen(
                viewModel = viewModel
            )
        }
    }
}
