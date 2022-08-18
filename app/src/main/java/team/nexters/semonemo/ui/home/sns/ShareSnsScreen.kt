package team.nexters.semonemo.ui.home.sns

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Black1
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.home.sns.component.StickerContent

@Composable
internal fun ShareSnsScreen(
    viewModel: ShareSnsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val place by remember { mutableStateOf("인왕산 클라이밍장") }
    val date by remember { mutableStateOf("2022년 7월 11일") }
    val activity = LocalContext.current as HomeActivity
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()
    val backgroundColor = Black1
    val captureController = rememberCaptureController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = backgroundColor
        )
    }
    LaunchedEffect(Unit){
        viewModel.getNewHoldList()
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                is ShareSnsEvent.ShareInstagram -> {
                    activity.onShareButtonClicked(event.bitmap.asAndroidBitmap())
                }
                ShareSnsEvent.NavigateToHome -> {
                    onBackPressed()
                }
            }
        }
    }
    StickerContent(
        captureController = captureController,
        onCaptured = { bitmap, _ ->
            bitmap?.let {
                viewModel.postEvent(ShareSnsEvent.ShareInstagram(bitmap))
            }
        },
        place = place,
        date = date,
        hold = R.drawable.hold1
    )
    ShareSnsScreen(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = backgroundColor,
        onBackPressed = { onBackPressed() },
        onShareButtonClicked = { captureController.capture() },
        place,
        date
    )
}

@Composable
private fun ShareSnsScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onBackPressed: () -> Unit,
    onShareButtonClicked: () -> Unit,
    place: String,
    date: String
) {
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
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                modifier = Modifier.size(148.dp),
                painter = painterResource(id = R.drawable.hold1),
                contentDescription = "hold"
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