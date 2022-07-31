package team.nexters.semonemo.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController



private val LightColors = lightColors(
    primary = Primary,
    onPrimary = White,
    secondary = Secondary,
    onSecondary = White,
    surface = White,
    onSurface = Gray9,
    background = White,
    onBackground = Gray9
)

@Composable
fun SemoNemoTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColors
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.background
        )
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}