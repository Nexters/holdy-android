package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.theme.Gray7
import team.nexters.semonemo.ui.home.model.MoimInfo

@Composable
internal fun MoimListColumn(
    moimList: List<MoimInfo>,
    navigateToMoimDetail: () -> Unit
) {
    val scrollState = rememberLazyListState()
    val size = moimList.size
    LazyColumn(
        modifier = Modifier.padding(top = 40.dp),
        state = scrollState
    ) {
        itemsIndexed(
            items = moimList,
            key = { index, _ ->
                index
            }
        ) { index, moimInfo ->
            Row {
                Column(
                    modifier = Modifier
                        .offset(y = 35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = stringResource(id = R.string.check)
                    )
                    if (index != size - 1) {
                        Image(
                            modifier = Modifier
                                .height(110.dp),
                            painter = painterResource(id = R.drawable.line),
                            contentDescription = stringResource(id = R.string.line)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                MoimListItem(
                    navigateToMoimDetail,
                    title = moimInfo.title,
                    place = moimInfo.place,
                    date = moimInfo.date,
                )
            }
        }
    }
}

@Composable
private fun MoimListItem(
    navigateToMoimDetail: () -> Unit,
    title: String,
    place: String,
    date: String
) {
    Column {
        Column(
            modifier = Modifier.clickable { navigateToMoimDetail() } //id가 인자로 넘어감
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = stringResource(R.string.location),
                )
                Text(
                    text = place,
                    style = MaterialTheme.typography.body2.copy(
                        color = Gray7
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = stringResource(id = R.string.calendar)
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.body2.copy(
                        color = Gray7
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}


@Composable
internal fun NoMoim(
    navigateToMoimCreate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.not_yet_moim),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Normal,
                color = Gray5
            )
        )
        Text(
            text = stringResource(id = R.string.not_yet_moim2),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Normal,
                color = Gray5
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .height(40.dp)
                .width(120.dp)
                .border(BorderStroke(1.dp, Gray3), RoundedCornerShape(100.dp))
                .clip(RoundedCornerShape(100.dp))
                .drawColoredShadow(alpha = 0.2f),
            onClick = { navigateToMoimCreate() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            text = stringResource(id = R.string.moim_create),
            shape = RoundedCornerShape(100.dp),
            textStyle = MaterialTheme.typography.body2.copy(
                color = Gray6
            )
        )

    }
}