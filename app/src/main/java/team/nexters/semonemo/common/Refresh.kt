package team.nexters.semonemo.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable

@Composable
fun Refresh(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val (isRefreshEnabled, setRefreshEnabled) = remember { mutableStateOf(true) }
    val (isRefreshRotated, setRefreshRotated) = remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (isRefreshRotated) 1080F else 0F,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            setRefreshEnabled(true)
        }
    )
    Image(
        modifier = modifier
            .noRippleClickable(enabled = isRefreshEnabled) {
                onClick()
                setRefreshRotated(!isRefreshRotated)
                setRefreshEnabled(false)
            }
            .rotate(angle),
        painter = painterResource(id = R.drawable.refresh),
        contentDescription = stringResource(id = R.string.refresh)
    )
}