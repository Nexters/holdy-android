package team.nexters.semonemo.ui.home.moimlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.domain.moim.model.MoimModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.EmptyScreen
import team.nexters.semonemo.common.ErrorScreen
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.White
import team.nexters.semonemo.ui.home.moimlist.component.EndMoimFilter
import team.nexters.semonemo.ui.home.moimlist.component.FloatingActionButton
import team.nexters.semonemo.ui.home.moimlist.component.MoimListColumn
import team.nexters.semonemo.ui.home.moimlist.component.TopBar
import team.nexters.semonemo.ui.home.navigation.HomeScreens

@Composable
internal fun MoimListScreen(
    viewModel: MoimListViewModel = hiltViewModel(),
    navigateToMoimCreate: () -> Unit,
    navigateToMoimDetail: (Int) -> Unit,
    navigateToHold: () -> Unit,
    navigateToShareSns: (String) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current
    val color = MaterialTheme.colors.background
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = color
        )
    }
    LaunchedEffect(Unit) {
        viewModel.fetchMoimList()
        viewModel.checkNewHold()
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                MoimListEvent.NavigateToHold -> {
                    navigateToHold()
                }
                MoimListEvent.NavigateToMoimCreate -> {
                    navigateToMoimCreate()
                }
                MoimListEvent.NavigateToShareSns -> {
                    navigateToShareSns(HomeScreens.List.route)
                }
                is MoimListEvent.NavigateToMoimDetail -> {
                    navigateToMoimDetail(event.id)
                }
            }
        }
    }
    val state = viewModel.uiState.collectAsState().value
    if (state.isNetworkError) {
        ErrorScreen(
            text = stringResource(id = R.string.network_error),
            onclick = { viewModel.refresh() }
        )
    } else {
        if (state.isLoading) {
            ProgressIndicator()
        } else {
            Scaffold(
                scaffoldState = scaffoldState,
            ) { contentPadding ->
                contentPadding
                MoimListScreen(
                    moims = state.moims,
                    navigateToMoimCreate = { viewModel.postEvent(MoimListEvent.NavigateToMoimCreate) },
                    navigateToMoimDetail = {
                        viewModel.postEvent(
                            MoimListEvent.NavigateToMoimDetail(
                                it
                            )
                        )
                    },
                    navigateToHold = { viewModel.postEvent(MoimListEvent.NavigateToHold) }
                )
            }
        }
    }
}

@Composable
private fun MoimListScreen(
    moims: List<MoimModel>,
    navigateToMoimCreate: () -> Unit = {},
    navigateToMoimDetail: (Int) -> Unit = {},
    navigateToHold: () -> Unit = {},
) {
    var isClosedMoimHide by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            TopBar(
                navigateToHold = navigateToHold
            )
            Spacer(modifier = Modifier.height(32.dp))
            if (moims.isEmpty()) {
                EmptyScreen(text = stringResource(id = R.string.not_yet_moim))
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    EndMoimFilter(
                        modifier = Modifier
                            .noRippleClickable { isClosedMoimHide = !isClosedMoimHide },
                        checked = isClosedMoimHide,
                        buttonText = stringResource(id = R.string.hide_finished_moim)
                    )
                }
                MoimListColumn(moims, navigateToMoimDetail, isClosedMoimHide)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, end = 20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                modifier = Modifier,
                onClick = { navigateToMoimCreate() }) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.cross),
                    tint = White,
                    contentDescription = stringResource(id = R.string.fab)
                )
            }
        }

    }
}