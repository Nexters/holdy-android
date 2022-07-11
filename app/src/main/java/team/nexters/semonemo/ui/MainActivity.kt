package team.nexters.semonemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import team.nexters.semonemo.ui.theme.SemoNemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           SemoNemoTheme{
               MainScreen()
           }
        }
    }
}

@Composable
fun MainScreen(){
    Text("셈넴")
}