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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.Participant
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.ui.home.moimdetail.component.InformationContent
import team.nexters.semonemo.ui.home.moimdetail.component.ParticipantContent
import team.nexters.semonemo.util.DateParser
import timber.log.Timber

@Composable
internal fun MoimDetailScreen(
    viewModel: MoimDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    id: Int
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Primary
        )
    }
    LaunchedEffect(Unit) {
        viewModel.getMoimDetail(id)
    }
    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                MoimDetailEvent.NavigateToMoimList -> {
                    onBackPressed()
                }
                is MoimDetailEvent.ShareKaKao -> {
                    kakaoShare(
                        context = context,
                        templateArgs = event.templeteArgs
                    )
                }
            }
        }
    }
    val state = viewModel.uiState.collectAsState().value
    if (state.loading) {
        ProgressIndicator()
    } else {
        MoimDetailScreen(
            onBackPressed = { viewModel.postEvent(MoimDetailEvent.NavigateToMoimList) },
            onInvite = { viewModel.postEvent(MoimDetailEvent.ShareKaKao(it)) },
            onAttendanceButtonClicked = viewModel::onAttendanceButtonClicked,
            onCameButtonClicked = viewModel::onCameButtonClicked,
            moimDetail = state.moimDetailModel!!,
            contentLoading = state.contentLoading
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MoimDetailScreen(
    onBackPressed: () -> Unit,
    onInvite: (Map<String, String>) -> Unit,
    onAttendanceButtonClicked: (Int, Boolean) -> Unit,
    onCameButtonClicked: (Int, Int, Boolean) -> Unit,
    moimDetail: MoimModel,
    contentLoading: Boolean
) {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    var isCome by remember { mutableStateOf(moimDetail.loginUser.wantToAttend) } // 갈게요 Button
    val parsedDate = DateParser.toTemplateArgs(moimDetail.startDate, moimDetail.endDate)
    val args = mapOf(
        "moim_id" to "${moimDetail.id}",
        "title" to moimDetail.place.summary,
        "content" to parsedDate
    )
    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        frontLayerContent = {
            ParticipantContent(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 32.dp),
                moim = moimDetail,
                scaffoldState = scaffoldState,
                contentLoading = contentLoading,
                onInvite = { onInvite(args) },
                onCameButtonClicked = onCameButtonClicked
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        frontLayerShape = RoundedCornerShape(8),
        backLayerContent = {
            InformationContent(
                modifier = Modifier.padding(start = 20.dp, top = 16.dp),
                title = moimDetail.place.summary,
                date = parsedDate,
                place = moimDetail.place.address,
                onBackPressed = onBackPressed
            )
        },
        backLayerBackgroundColor = colors.primary
    )
    if (moimDetail.loginUser.isHost.not()) {
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
                onClick = {
                    isCome = !isCome
                    onAttendanceButtonClicked(moimDetail.id, isCome)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor
                ),
                text = if (isCome) {
                    stringResource(id = R.string.not_go)
                } else {
                    stringResource(id = R.string.go)
                },
                textColor = textColor,
                enabled = moimDetail.isEnd.not()
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
        ShareClient.instance.shareCustom(
            context,
            templateId,
            templateArgs
        ) { sharingResult, error ->
            if (error != null) {
                Timber.e("카카오톡 공유 실패", error)
            } else if (sharingResult != null) {
                Timber.d("카카오톡 공유 성공 ${sharingResult.intent}")
                ContextCompat.startActivity(context, sharingResult.intent, null)

                // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                Timber.w("Warning Msg: ${sharingResult.warningMsg}")
                Timber.w("Argument Msg: ${sharingResult.argumentMsg}")
            }
        }
    } else {
        // 카카오톡 미설치: 웹 공유 사용 권장
        // 웹 공유 예시 코드
        val sharerUrl = WebSharerClient.instance.makeCustomUrl(templateId)

        // CustomTabsServiceConnection 지원 브라우저 열기
        // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
        try {
            KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
        } catch (e: UnsupportedOperationException) {
            // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
        }
    }
}