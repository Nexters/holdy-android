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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.domain.hold.model.Hold
import team.nexters.domain.user.model.LoginModel
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.home.hold.component.HoldContent
import team.nexters.semonemo.ui.home.hold.component.InformationContent
import team.nexters.semonemo.ui.home.navigation.HomeScreens

@Composable
internal fun HoldScreen(
    viewModel: HoldViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    navigateToShareSns: (String) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    LaunchedEffect(Unit) {
        viewModel.init()
        viewModel.checkNewHold()
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                HoldEvent.NavigateToHold -> {
                    navigateToShareSns(HomeScreens.Hold.route)
                }
            }
        }
    }

    val state = viewModel.uiState.collectAsState().value
    if (state.loading || state.myInfo == null) {
        ProgressIndicator()
    } else {
        HoldContent(
            onBackPressed = onBackPressed,
            myInfo = state.myInfo,
            holds = state.holds
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HoldContent(
    onBackPressed: () -> Unit = {},
    myInfo: LoginModel,
    holds: List<Hold>
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        frontLayerContent = {
            HoldContent(
                modifier = Modifier.fillMaxSize(),
                holds = holds
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        frontLayerShape = RoundedCornerShape(8),
        backLayerContent = {
            InformationContent(
                modifier = Modifier.padding(top = 16.dp, bottom = 40.dp),
                profile = myInfo.profileImageUrl,
                nickname = myInfo.nickname,
                club = myInfo.group,
                onBackPressed = onBackPressed
            )
        },
        backLayerBackgroundColor = MaterialTheme.colors.primary
    )
}