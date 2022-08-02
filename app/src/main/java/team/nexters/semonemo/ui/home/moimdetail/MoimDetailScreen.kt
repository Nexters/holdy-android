package team.nexters.semonemo.ui.home.moimdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.home.model.MoimInfo
import team.nexters.semonemo.ui.home.model.MoimInfoDummy
import team.nexters.semonemo.ui.home.moimdetail.component.InformationContent
import team.nexters.semonemo.ui.home.moimdetail.component.ParticipantContent
import team.nexters.semonemo.ui.home.moimlist.component.TopBar

@Composable
internal fun MoimDetailScreen(
    viewModel: MoimDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    MoimDetailScreen(onBackPressed)
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun MoimDetailScreen(
    onBackPressed: () -> Unit = {},
    moimInfo: MoimInfo = MoimInfoDummy
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val isHostMode by remember { mutableStateOf(true) } // 호스트 판단 추후 변경
    var isCome by remember { mutableStateOf(false) } // 갈게요 Button
    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        frontLayerContent = {
            ParticipantContent(
                isHostMode = isHostMode,
                scaffoldState
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        frontLayerShape = RoundedCornerShape(8),
        backLayerContent = {
            InformationContent(
                title = moimInfo.title,
                date = moimInfo.date,
                place = moimInfo.place,
                onBackPressed = onBackPressed
            )
        },
        backLayerBackgroundColor = colors.primary
    )
    if (!isHostMode) {
        val buttonColor = if (isCome) {
            colors.background
        } else {
            colors.primary
        }
        val textColor = if (isCome) {
            colors.onBackground
        } else {
            colors.onPrimary
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .padding(horizontal = 20.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = { isCome = !isCome },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor
                ),
                text = if (isCome) {
                    stringResource(id = R.string.not_go)
                } else {
                    stringResource(id = R.string.go)
                },
                textColor = textColor
            )
        }
    }
}

