package team.nexters.semonemo.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.navigation.HomeNavigation
import team.nexters.semonemo.ui.home.navigation.HomeScreens

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                HomeNavigation(navController = navController)
                BackHandler {
                    coroutineScope.launch {
                        navController.navigate(HomeScreens.List.route)
                    }
                }
            }
        }
    }
}
