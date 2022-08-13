package team.nexters.semonemo.ui.home.moimdetail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.home.model.MoimInfo
import team.nexters.semonemo.ui.home.model.MoimInfoDummy
import team.nexters.semonemo.ui.home.model.participantsDummy
import team.nexters.semonemo.ui.home.moimdetail.component.InformationContent
import team.nexters.semonemo.ui.home.moimdetail.component.ParticipantContent
import timber.log.Timber

@Composable
internal fun MoimDetailScreen(
    viewModel: MoimDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    MoimDetailScreen(onBackPressed)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MoimDetailScreen(
    onBackPressed: () -> Unit = {},
    moimInfo: MoimInfo = MoimInfoDummy
) {
    val context = LocalContext.current
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val isHostMode by remember { mutableStateOf(true) } // 호스트 판단 추후 변경
    var isCome by remember { mutableStateOf(false) } // 갈게요 Button
    val args = mapOf("moim_id" to "${moimInfo.id}", "title" to moimInfo.title, "content" to moimInfo.date) // moim id 넣어줘 상록
    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        frontLayerContent = {
            ParticipantContent(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 32.dp),
                isHostMode = isHostMode,
                scaffoldState = scaffoldState,
                participant = participantsDummy,
                onInvite = { kakaoShare(context, templateArgs = args ) },
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        frontLayerShape = RoundedCornerShape(8),
        backLayerContent = {
            InformationContent(
                modifier = Modifier.padding(start = 20.dp, top = 16.dp),
                title = moimInfo.title,
                date = moimInfo.date,
                place = moimInfo.place,
                onBackPressed = onBackPressed
            )
        },
        backLayerBackgroundColor = colors.primary
    )
    if (!isHostMode) {
        val buttonColor = if (isCome) {
            colors.background
        } else {
            colors.primary
        }
        val textColor = if (isCome) {
            colors.onBackground
        } else {
            colors.onPrimary
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .padding(horizontal = 20.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = { isCome = !isCome },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor
                ),
                text = if (isCome) {
                    stringResource(id = R.string.not_go)
                } else {
                    stringResource(id = R.string.go)
                },
                textColor = textColor
            )
        }
    }
}


// kakaoShare 카카오 공유하기
// https://developers.kakao.com/docs/latest/ko/message/android-link#custom-template-msg
private fun kakaoShare(
    context: Context,
    templateId: Long = 81345,
    templateArgs: Map<String, String>? = null,
) {
    if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
        // 카카오톡으로 카카오톡 공유 가능
        ShareClient.instance.shareCustom(context, templateId, templateArgs) { sharingResult, error ->
            if (error != null) {
                Timber.e("카카오톡 공유 실패", error)
            }
            else if (sharingResult != null) {
                Timber.d("카카오톡 공유 성공 ${sharingResult.intent}")
                ContextCompat.startActivity(context, sharingResult.intent, null)

                // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                Timber.w("Warning Msg: ${sharingResult.warningMsg}")
                Timber.w("Argument Msg: ${sharingResult.argumentMsg}")
            }
        }
    }
    else {
        // 카카오톡 미설치: 웹 공유 사용 권장
        // 웹 공유 예시 코드
        val sharerUrl = WebSharerClient.instance.makeCustomUrl(templateId)

        // CustomTabsServiceConnection 지원 브라우저 열기
        // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
        try {
            KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
        } catch(e: UnsupportedOperationException) {
            // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
        }
    }
}