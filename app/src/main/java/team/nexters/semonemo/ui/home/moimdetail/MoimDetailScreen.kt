package team.nexters.semonemo.ui.home.moimdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.semonemo.R
import team.nexters.semonemo.ui.home.moimlist.component.TopBar

@Composable
internal fun MoimDetailScreen(
    viewModel: MoimDetailViewModel = hiltViewModel()
) {

}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun MoimDetailScreen() {
    BackdropScaffold(
        appBar = {  },
        frontLayerContent = {
            TopBar(
                title = stringResource(id = R.string.moim_title),
                painter = painterResource(id = R.drawable.holdy1),
                contentDescription = stringResource(id = R.string.holdy1)
            )
        },
        backLayerContent = {
            Column() {
                TopBar(
                    title = stringResource(id = R.string.moim_title),
                    painter = painterResource(id = R.drawable.holdy1),
                    contentDescription = stringResource(id = R.string.holdy1)
                )
                TopBar(
                    title = stringResource(id = R.string.moim_title),
                    painter = painterResource(id = R.drawable.holdy1),
                    contentDescription = stringResource(id = R.string.holdy1)
                )
                TopBar(
                    title = stringResource(id = R.string.moim_title),
                    painter = painterResource(id = R.drawable.holdy1),
                    contentDescription = stringResource(id = R.string.holdy1)
                )
            }
        },
        backLayerBackgroundColor = MaterialTheme.colors.primary
    ) {

    }
}

