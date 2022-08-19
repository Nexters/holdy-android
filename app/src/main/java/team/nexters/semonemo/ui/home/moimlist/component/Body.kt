package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import team.nexters.domain.moim.model.MoimModel
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.theme.Gray7
import team.nexters.semonemo.util.DateParser

@Composable
internal fun MoimListColumn(
    moims: List<MoimModel>,
    navigateToMoimDetail: (Int) -> Unit,
    isClosedMoimHide: Boolean
) {

    val filteredMoims = if (isClosedMoimHide) {
        moims.filter { it.isEnd.not() }
    } else {
        moims
    }
    val scrollState = rememberLazyListState()
    val size = filteredMoims.size
    LazyColumn(
        modifier = Modifier.padding(top = 40.dp),
        state = scrollState
    ) {
        itemsIndexed(
            items = filteredMoims,
            key = { index, _ ->
                index
            }
        ) { index, moim ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .offset(y = 35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            getTimeLineIcon(
                                isEnd = moim.isEnd,
                                isHost = moim.loginUser.isHost,
                                wanToAttend = moim.loginUser.wantToAttend
                            )
                        ),
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
                    modifier = Modifier.fillMaxWidth(),
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

private fun getTimeLineIcon(
    isEnd: Boolean,
    isHost: Boolean,
    wanToAttend: Boolean
): Int {
    return if (isEnd) {
        if (wanToAttend) {
            R.drawable.uncheck
        } else {
            R.drawable.correct
        }
    } else {
        if (isHost) {
            R.drawable.crown
        } else {
            R.drawable.check
        }
    }
}


@Composable
private fun MoimListItem(
    modifier: Modifier = Modifier,
    navigateToMoimDetail: (Int) -> Unit,
    id: Int,
    title: String,
    place: String,
    date: String
) {
    Column(modifier = modifier) {
        Column(modifier = modifier.clickable { navigateToMoimDetail(id) }) {
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