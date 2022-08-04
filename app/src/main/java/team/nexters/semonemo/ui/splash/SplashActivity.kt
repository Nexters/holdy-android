package team.nexters.semonemo.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.theme.Tertiary

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                SplashScreen()
            }
        }

    }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Tertiary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}
