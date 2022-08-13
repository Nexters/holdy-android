package team.nexters.semonemo.ui.home.model

import androidx.compose.runtime.Stable

data class MoimInfo(
    val id: Long,
    val title: String,
    val place: String,
    val date: String
)

@Stable
val MoimInfoDummy = MoimInfo(
    1,
    "종로문화체육센터 클라이밍장",
    "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
    "2022년 7월 22일 월요일 오후 2시"
)

@Stable
val moimListDummy = listOf(
    MoimInfo(
        1,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        2,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        3,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        4,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        5,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
    MoimInfo(
        6,
        "종로문화체육센터 클라이밍장",
        "서울 종로구 인왕산로1길 21 (사직동) 지하 1층",
        "2022년 7월 22일 월요일 오후 2시"
    ),
)