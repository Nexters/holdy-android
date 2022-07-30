package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Gray7


data class MoimInfo(
    val title: String,
    val place: String,
    val date: String
)

val moimListDummy = listOf(
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
)

@Composable
internal fun MoimListColumn(
    moimList: List<MoimInfo> = moimListDummy,
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
                    modifier = Modifier.offset(y = 35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = stringResource(id = R.string.check)
                    )
                    if (index != size - 1) {
                        Image(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .height(100.dp),
                            painter = painterResource(id = R.drawable.line),
                            contentDescription = stringResource(id = R.string.line)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                MoimListItem(
                    title = moimInfo.title,
                    place = moimInfo.place,
                    date = moimInfo.date
                )
            }
        }
    }
}

@Composable
private fun MoimListItem(
    title: String,
    place: String,
    date: String
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onBackground
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
                style = MaterialTheme.typography.bodyMedium.copy(
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
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Gray7
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}


@Composable
internal fun TimeLine(
    moimList: List<MoimInfo> = moimListDummy,
) {

}


/* 회의 후 결정
@Composable
internal fun NoMoim() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }
}
 */