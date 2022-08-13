package team.nexters.semonemo.ui.home.sns

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Black1
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.theme.Primary

@Composable
internal fun ShareSnsScreen(
    onBackPressed: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val backgroundColor = Black1
    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(
            color = backgroundColor
        )
    }
    ShareSnsScreen(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = backgroundColor
    )
}

@Composable
private fun ShareSnsScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onBackPressed: () -> Unit = {},
    place: String = "인왕산 클라이밍장",
    date: String = "2022년 7월 11일"
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
                onClick = { /*TODO*/ },
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

