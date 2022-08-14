package team.nexters.semonemo.ui.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.start.login.LoginScreen

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                LoginScreen()
            }
        }
    }

    fun startMain() {
        startActivity(HomeActivity.newIntent(this))
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.none)
        finish()
    }
}
