package team.nexters.semonemo.ui.home.model

import androidx.annotation.DrawableRes
import team.nexters.semonemo.R

data class Participant(
    @DrawableRes val profile: Int,
    val nickname: String,
    val team: String,
    val isLeader: Boolean = false
)

val participantsDummy = listOf(
    Participant(
        R.drawable.holdy1,
        "등산왕 마속",
        "고구마 클럽",
        true
    ),
    Participant(
        R.drawable.holdy1,
        "탑동 엄홍길",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "수내역 황금허벅지 김민철",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "Im혜지",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "수내역 황금허벅지 김민철",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "Im혜지",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "탑동 엄홍길",
        "고구마 클럽",
    ),
    Participant(
        R.drawable.holdy1,
        "탑동 엄홍길",
        "고구마 클럽",
    ),
)