package team.nexters.semonemo.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.start.login.LoginScreen

@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                LoginScreen()
            }
        }
    }

    fun startMain() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
