package team.nexters.semonemo.ui.home.hold

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.home.hold.component.HoldContent
import team.nexters.semonemo.ui.home.hold.component.InformationContent

@Composable
internal fun HoldScreen(
    viewModel: HoldViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    HoldScreen(
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun HoldScreen(
    onBackPressed: () -> Unit = {}
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        frontLayerContent = {
            HoldContent(
                modifier = Modifier
                    .fillMaxSize()
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        frontLayerShape = RoundedCornerShape(8),
        backLayerContent = {
            InformationContent(
                modifier = Modifier.padding(top = 16.dp, bottom = 40.dp),
                nickname = "나무타기 달인",
                club = "고구미 클럽",
                onBackPressed = onBackPressed
            )
        },
        backLayerBackgroundColor = MaterialTheme.colors.primary
    )
}