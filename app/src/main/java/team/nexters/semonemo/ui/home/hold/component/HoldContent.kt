package team.nexters.semonemo.ui.home.hold.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Gray5

@Composable
internal fun HoldContent(
    modifier: Modifier = Modifier,
    count: Int = 16
) {
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
                contentDescription = "hold_background"
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

                /*
                 임시 홀드 배치, 어떻게 하면 좋을까 이거
                 */
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.hold1),
                        contentDescription = "hold"
                    )
                    Image(
                        painterResource(id = R.drawable.hold2),
                        contentDescription = "hold"
                    )
                    Image(
                        painterResource(id = R.drawable.hold3),
                        contentDescription = "hold"
                    )
                    Image(
                        painterResource(id = R.drawable.hold4),
                        contentDescription = "hold"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.hold5),
                        contentDescription = "hold"
                    )
                    Image(
                        painterResource(id = R.drawable.hold6),
                        contentDescription = "hold"
                    )
                    Image(
                        painterResource(id = R.drawable.hold7),
                        contentDescription = "hold"
                    )
                }
            }
        }
    }
}
