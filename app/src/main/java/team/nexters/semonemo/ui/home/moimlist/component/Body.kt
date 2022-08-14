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
import team.nexters.domain.moim.model.MoimModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.theme.Gray7
import team.nexters.semonemo.util.DateParser

@Composable
internal fun MoimListColumn(
    moims: List<MoimModel>,
    navigateToMoimDetail: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()
    val size = moims.size
    LazyColumn(
        modifier = Modifier.padding(top = 40.dp),
        state = scrollState
    ) {
        itemsIndexed(
            items = moims,
            key = { index, _ ->
                index
            }
        ) { index, moim ->
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
                    id = moim.id,
                    title = moim.place.summary,
                    place = moim.place.address,
                    date = DateParser.toStartDate(moim.startDate),
                )
            }
        }
    }
}

@Composable
private fun MoimListItem(
    navigateToMoimDetail: (Int) -> Unit,
    id: Int,
    title: String,
    place: String,
    date: String
) {
    Column {
        Column(
            modifier = Modifier.clickable { navigateToMoimDetail(id) } //id가 인자로 넘어감
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
        Image(
            painter = painterResource(id = R.drawable.lying_holdy),
            contentDescription = stringResource(id = R.string.lying_holdy)
        )
        Spacer(modifier = Modifier.height(30.dp))
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
    }
}