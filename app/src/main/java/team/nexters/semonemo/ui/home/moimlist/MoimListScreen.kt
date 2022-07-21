package team.nexters.semonemo.ui.home.moimlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MoimListScreen(
    viewModel: MoimListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        viewModel.fetchMoimList()
    }
}