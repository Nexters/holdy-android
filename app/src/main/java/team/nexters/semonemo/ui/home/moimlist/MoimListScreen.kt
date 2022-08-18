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
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.White
import team.nexters.semonemo.ui.home.moimlist.component.EndMoimFilter
import team.nexters.semonemo.ui.home.moimlist.component.FloatingActionButton
import team.nexters.semonemo.ui.home.moimlist.component.MoimListColumn
import team.nexters.semonemo.ui.home.moimlist.component.NoMoim
import team.nexters.semonemo.ui.home.moimlist.component.TopBar

@Composable
internal fun MoimListScreen(
    viewModel: MoimListViewModel = hiltViewModel(),
    navigateToMoimCreate: () -> Unit,
    navigateToMoimDetail: (Int) -> Unit,
    navigateToHold: () -> Unit
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
                is MoimListEvent.NavigateToMoimDetail -> {
                    navigateToMoimDetail(event.id)
                }
            }
        }
    }
    when (val state = viewModel.uiState.collectAsState().value) {
        is MoimListState.Success -> {
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
        else -> {
            ProgressIndicator()
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
    var isHide by remember { mutableStateOf(false) }
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
            Spacer(modifier = Modifier.height(19.dp))
            TopBar(
                title = stringResource(id = R.string.moim_title),
                painter = painterResource(id = R.drawable.small_holdy1),
                contentDescription = stringResource(id = R.string.holdy),
                navigateToHold = navigateToHold
            )
            Spacer(modifier = Modifier.height(32.dp))
            if (moims.isEmpty()) {
                NoMoim(navigateToMoimCreate)
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    EndMoimFilter(
                        modifier = Modifier
                            .noRippleClickable { isHide = !isHide },
                        checked = isHide,
                        buttonText = stringResource(id = R.string.hide_finished_moim)
                    )
                }
                MoimListColumn(moims, navigateToMoimDetail)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, end = 20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
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