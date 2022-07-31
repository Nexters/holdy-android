package team.nexters.semonemo.ui.home.moimlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.White
import team.nexters.semonemo.ui.home.moimlist.component.EndMoimFilter
import team.nexters.semonemo.ui.home.moimlist.component.FloatingActionButton
import team.nexters.semonemo.ui.home.moimlist.component.MoimListColumn
import team.nexters.semonemo.ui.home.moimlist.component.NoMoim
import team.nexters.semonemo.ui.home.moimlist.component.TopBar

@Composable
internal fun MoimListScreen(
    viewModel: MoimListViewModel = hiltViewModel(),
    navigateToMoimCreate: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchMoimList()
    }
    MoimListScreen(navigateToMoimCreate)
}

@Preview
@Composable
private fun MoimListScreen(
    navigateToMoimCreate: () -> Unit = {}
) {
    val moimList = emptyList<MoimInfo>()
    var isHide by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            TopBar(
                title = stringResource(id = R.string.moim_title),
                painter = painterResource(id = R.drawable.holdy1),
                contentDescription = stringResource(id = R.string.holdy1)
            )
            Spacer(modifier = Modifier.height(32.dp))
            if (moimList.isEmpty()) {
                NoMoim(navigateToMoimCreate)
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    EndMoimFilter(
                        checked = isHide,
                        onCheckedChanged = { isHide = !isHide },
                        buttonText = stringResource(id = R.string.hide_finished_moim)
                    )
                }
                MoimListColumn(moimList)
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