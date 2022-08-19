package team.nexters.semonemo.ui.home.sns

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import team.nexters.domain.hold.model.NewHold
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Black1
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.home.hold.component.getHoldRes
import team.nexters.semonemo.ui.home.sns.component.StickerContent
import team.nexters.semonemo.util.DateParser

@Composable
internal fun ShareSnsScreen(
    viewModel: ShareSnsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val activity = LocalContext.current as HomeActivity
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()
    val backgroundColor = Black1
    val captureController = rememberCaptureController()
    BackHandler {
        onBackPressed()
    }
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = backgroundColor
        )
    }
    LaunchedEffect(Unit) {
        viewModel.getNewHoldList()
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                is ShareSnsEvent.ShareInstagram -> {
                    activity.onShareButtonClicked(event.bitmap.asAndroidBitmap())
                }
                ShareSnsEvent.NavigateToBackRoute -> {
                    onBackPressed()
                }
            }
        }
    }
    val state = viewModel.uiState.collectAsState().value
    if (state.loading) {
        ProgressIndicator()
    } else {
        ShareSnsScreen(
            modifier = Modifier.fillMaxSize(),
            captureController = captureController,
            backgroundColor = backgroundColor,
            onBackPressed = { onBackPressed() },
            onShareButtonClicked = { captureController.capture() },
            onCaptured = { viewModel.postEvent(ShareSnsEvent.ShareInstagram(it)) },
            state.newHolds
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ShareSnsScreen(
    modifier: Modifier = Modifier,
    captureController: CaptureController,
    backgroundColor: Color,
    onBackPressed: () -> Unit,
    onShareButtonClicked: () -> Unit,
    onCaptured: (ImageBitmap) -> Unit,
    newHolds: List<NewHold>
) {
    val pagerState = rememberPagerState()
    val currentHold = newHolds[pagerState.currentPage]
    StickerContent(
        captureController = captureController,
        onCaptured = { bitmap, _ ->
            bitmap?.let {
                onCaptured(bitmap)
            }
        },
        place = currentHold.moim.place.summary,
        date = DateParser.toYearMonthDay(currentHold.moim.endDate),
        hold = getHoldRes(order = currentHold.order)
    )
    Surface(
        modifier = modifier,
        color = backgroundColor
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.sns_background),
                contentDescription = stringResource(id = R.string.sns_background)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 100.dp),
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.showing_hold1))
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(stringResource(id = R.string.showing_hold2))
                    }
                    append(stringResource(id = R.string.showing_hold3))
                },
                style = MaterialTheme.typography.h3.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(50.dp))
            HorizontalPager(
                modifier = Modifier.height(310.dp),
                state = pagerState,
                count = newHolds.size
            ) { page ->
                val newHold = newHolds[page]
                NewHoldContent(
                    order = newHold.order,
                    place = newHold.moim.place.summary,
                    date = DateParser.toYearMonthDay(newHold.moim.endDate)
                )
            }
            Spacer(modifier = Modifier.height(53.dp))
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = { onShareButtonClicked() },
                shape = RoundedCornerShape(8.dp),
                text = stringResource(id = R.string.share_instagram)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 16.dp, end = 20.dp)
                    .noRippleClickable { onBackPressed() },
                painter = painterResource(id = R.drawable.close_white),
                contentDescription = stringResource(id = R.string.close)
            )
        }
    }
}

@Composable
private fun NewHoldContent(
    order: Int,
    place: String,
    date: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(148.dp),
            painter = painterResource(id = getHoldRes(order = order)),
            contentDescription = stringResource(id = R.string.hold)
        )
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = place,
            style = MaterialTheme.typography.h3.copy(
                color = MaterialTheme.colors.onPrimary
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = date,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = Gray5
            ),
        )
    }
}