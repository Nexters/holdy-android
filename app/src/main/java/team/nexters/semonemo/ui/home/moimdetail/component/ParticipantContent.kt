package team.nexters.semonemo.ui.home.moimdetail.component

import android.content.ActivityNotFoundException
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.github.skgmn.composetooltip.AnchorEdge
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import kotlinx.coroutines.launch
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.Tooltip
import team.nexters.semonemo.theme.Danger1
import team.nexters.semonemo.theme.Gray0
import team.nexters.semonemo.theme.Gray1
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.ui.home.model.participantsDummy
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ParticipantContent(
    modifier: Modifier = Modifier,
    isHostMode: Boolean = false,
    scaffoldState: BackdropScaffoldState
) {
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val snackbarCoroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
    ) {
        InfoBox(isHostMode)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.moim_participant),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Row {
                if (participantsDummy.size == 1) {
                    Tooltip(
                        text = stringResource(id = R.string.send_link_moim_invite),
                        maxLines = 1,
                        anchorEdge = AnchorEdge.Start
                    )
                }
                if (isHostMode) {
                    Image(
                        modifier = Modifier.clickable {
                            snackbarCoroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(context.getString(R.string.link_copy))
                            }
                            kakaoShare(context, 81345)
                        },
                        painter = painterResource(id = R.drawable.invite),
                        contentDescription = stringResource(id = R.string.invite)
                    )
                }
            }
        }
        LazyColumn(
            state = scrollState
        ) {
            items(participantsDummy) { participant ->
                ParticipantItem(
                    profile = participant.profile,
                    nickname = participant.nickname,
                    team = participant.team,
                    isLeader = participant.isLeader,
                    isHostMode = isHostMode
                )
            }
        }
    }
}

private fun kakaoShare(context: Context, templateId: Long) {
    if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
        // 카카오톡으로 카카오톡 공유 가능
        ShareClient.instance.shareCustom(context, templateId) { sharingResult, error ->
            if (error != null) {
                Timber.e("카카오톡 공유 실패", error)
            }
            else if (sharingResult != null) {
                Timber.d("카카오톡 공유 성공 ${sharingResult.intent}")
                startActivity(context, sharingResult.intent, null)

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

@Composable
private fun ParticipantItem(
    profile: Int,
    nickname: String,
    team: String,
    isLeader: Boolean,
    isHostMode: Boolean
) {
    var isCome by remember { mutableStateOf(false) }
    val buttonColor = if (isCome) {
        MaterialTheme.colors.background
    } else {
        MaterialTheme.colors.primary
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = profile),
                    contentDescription = stringResource(id = R.string.holdy)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.padding(vertical = 6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = nickname,
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        if (isLeader) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.crown_badge),
                                contentDescription = stringResource(id = R.string.crown_badge)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = team,
                        style = MaterialTheme.typography.caption.copy(
                            color = Gray6
                        )
                    )
                }
            }
            if (isHostMode && !isLeader) {
                Button(
                    modifier = Modifier.height(24.dp),
                    onClick = { isCome = !isCome },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor
                    ),
                    text = if (isCome) {
                        stringResource(id = R.string.not_come)
                    } else {
                        stringResource(id = R.string.come)
                    },
                    textStyle = MaterialTheme.typography.caption,
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        end = 8.dp,
                        top = 3.dp,
                        bottom = 4.dp
                    )
                )
            }
        }
        Divider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = Gray1
        )
    }

}

@Composable
internal fun InfoBox(
    isHostMode: Boolean
) {
    val firstSection = if (isHostMode) {
        stringResource(id = R.string.host_check_info1)
    } else {
        stringResource(id = R.string.check_info1)
    }

    val secondSection = if (isHostMode) {
        stringResource(id = R.string.host_check_info2)
    } else {
        stringResource(id = R.string.check_info2)
    }

    val thirdSection = if (isHostMode) {
        stringResource(id = R.string.host_check_info3)
    } else {
        stringResource(id = R.string.check_info3)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray0),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.padding(vertical = 14.dp),
                painter = painterResource(id = R.drawable.info),
                contentDescription = stringResource(id = R.string.info)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = buildAnnotatedString {
                    append(firstSection)
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Danger1
                        )
                    ) {
                        append(secondSection)
                    }
                    append(thirdSection)
                },
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}