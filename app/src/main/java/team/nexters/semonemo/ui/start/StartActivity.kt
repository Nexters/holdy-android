package team.nexters.semonemo.ui.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.start.navigation.StartNavigation

@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, StartActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                StartNavigation(navController = rememberNavController())
            }
        }
    }

    fun startMain() {
        startActivity(HomeActivity.newIntent(this))
        finish()
    }
}
