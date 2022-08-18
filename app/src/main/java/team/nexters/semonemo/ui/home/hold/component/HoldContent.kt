package team.nexters.semonemo.ui.home.hold.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.nexters.domain.hold.model.Hold
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Gray5

@Immutable
object TotalHold {
    @Stable
    const val count = 18
}

@Composable
internal fun HoldContent(
    modifier: Modifier = Modifier,
    holds: List<Hold>
) {
    val count = holds.size
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.hold_background),
                contentDescription = stringResource(id = R.string.hold_background)
            )
            Column(
                modifier = Modifier.padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${count}개",
                    style = MaterialTheme.typography.h3.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.collect_hold),
                    style = MaterialTheme.typography.body1.copy(
                        color = Gray5,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var holdCount = 1
                    holds.chunked(7).forEach { list7 ->
                        list7.chunked(4).forEach { list4 ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                list4.forEach { hold ->
                                    Hold(
                                        holdRes = getHoldRes(order = holdCount++),
                                        hold.moim.loginUser.isHost
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun Hold(
    @DrawableRes holdRes: Int,
    isHost: Boolean = false,
) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painterResource(id = holdRes),
            contentDescription = stringResource(id = R.string.hold)
        )
        if (isHost) {
            Image(
                painterResource(id = R.drawable.hold_crown),
                contentDescription = stringResource(id = R.string.hold_crown)
            )
        }
    }
}

@Composable
internal fun getHoldRes(order: Int): Int =
    when ((order) % TotalHold.count) {
        1 -> R.drawable.hold1
        2 -> R.drawable.hold2
        3 -> R.drawable.hold3
        4 -> R.drawable.hold4
        5 -> R.drawable.hold5
        6 -> R.drawable.hold6
        7 -> R.drawable.hold7
        8 -> R.drawable.hold8
        9 -> R.drawable.hold9
        10 -> R.drawable.hold10
        11 -> R.drawable.hold11
        12 -> R.drawable.hold12
        13 -> R.drawable.hold13
        14 -> R.drawable.hold14
        else -> R.drawable.hold15
    }
