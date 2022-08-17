package team.nexters.semonemo.ui.home.sns

import androidx.compose.ui.graphics.ImageBitmap

sealed class ShareSnsEvent {
    object NavigateToHome : ShareSnsEvent()
    data class ShareInstagram(val bitmap: ImageBitmap) : ShareSnsEvent()
}