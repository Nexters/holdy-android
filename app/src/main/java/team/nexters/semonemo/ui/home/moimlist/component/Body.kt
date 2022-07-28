package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.White


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
    )
)

@Composable
internal fun MoimListColumn(
    moimList: List<MoimInfo> = moimListDummy
) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(moimList) { moimInfo ->
            MoimListItem(
                title = moimInfo.title,
                place = moimInfo.place,
                date = moimInfo.date
            )
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
        Text(title)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = place)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = date)
        Spacer(modifier = Modifier.height(40.dp))
    }
}


@Composable
internal fun TimeLine() {

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