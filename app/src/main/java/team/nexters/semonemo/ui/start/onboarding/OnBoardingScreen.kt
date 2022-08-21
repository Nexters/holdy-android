package team.nexters.semonemo.ui.start.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.theme.Heading2Medium
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.start.StartEvent
import team.nexters.semonemo.ui.start.StartActivity
import team.nexters.semonemo.ui.start.StartViewModel

object Page {
    @Stable
    const val count = 4
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun OnBoardingScreen(
    viewModel: StartViewModel,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity = LocalContext.current as StartActivity
    val systemUiController = rememberSystemUiController()
    val count = Page.count
    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                StartEvent.OnBoardingFinished ->
                    activity.startMain()
                else -> {

                }
            }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            count = count
        ) { page ->
            OnBoardingContent(
                pagerState = pagerState,
                page = page,
                image = getHoldyImage(page),
                introduce = getIntroduceText(page),
                onStartButtonClicked = { viewModel.onStartButtonClicked() }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun OnBoardingContent(
    pagerState: PagerState,
    page: Int,
    @DrawableRes image: Int,
    @StringRes introduce: Int,
    onStartButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.on_boarding_holdy)
        )
        Text(
            text = stringResource(id = introduce),
            style = Heading2Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(164.dp))
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (page == Page.count - 1) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 40.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = { onStartButtonClicked() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onPrimary
                ),
                textColor = MaterialTheme.colors.primary,
                text = stringResource(id = R.string.start)
            )
        } else {
            HorizontalPagerIndicator(
                modifier = Modifier.padding(bottom = 56.dp),
                pagerState = pagerState,
                inactiveColor = MaterialTheme.colors.onSecondary,
                activeColor = MaterialTheme.colors.secondary,
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp
            )
        }
    }
}


private fun getHoldyImage(page: Int) = when (page) {
    0 -> R.drawable.onboarding_holdy1
    1 -> R.drawable.onboarding_holdy2
    2 -> R.drawable.onboarding_holdy3
    else -> R.drawable.onboarding_holdy4
}


private fun getIntroduceText(page: Int) = when (page) {
    0 -> R.string.on_Boarding1
    1 -> R.string.on_Boarding2
    2 -> R.string.on_Boarding3
    else -> R.string.on_Boarding4
}
