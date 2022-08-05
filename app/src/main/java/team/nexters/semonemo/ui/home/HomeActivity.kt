package team.nexters.semonemo.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.navigation.NavigationGraph

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
                NavigationGraph(navController = rememberNavController())
            }
        }
    }
}