package team.nexters.semonemo.ui.home.moimdetail.component

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
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.github.skgmn.composetooltip.AnchorEdge
import kotlinx.coroutines.launch
import team.nexters.domain.moim.model.MoimModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.ProgressIndicator
import team.nexters.semonemo.common.Tooltip
import team.nexters.semonemo.theme.Danger1
import team.nexters.semonemo.theme.Gray0
import team.nexters.semonemo.theme.Gray1
import team.nexters.semonemo.theme.Gray6

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ParticipantContent(
    modifier: Modifier = Modifier,
    moim: MoimModel,
    scaffoldState: BackdropScaffoldState,
    contentLoading: Boolean,
    onInvite: () -> Unit,
    onCameButtonClicked: (Int, Int, Boolean) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val snackBarCoroutineScope = rememberCoroutineScope()
    val isHostMode = moim.loginUser.isHost
    val participants = moim.participants
    Column(
        modifier = modifier
    ) {
        InfoBox(moim.loginUser.isHost)
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
                if (participants.size == 1 && isHostMode) {
                    Tooltip(
                        text = stringResource(id = R.string.send_link_moim_invite),
                        maxLines = 1,
                        anchorEdge = AnchorEdge.Start
                    )
                }
                if (isHostMode) {
                    Image(
                        modifier = Modifier.clickable {
                            onInvite()
                            snackBarCoroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(context.getString(R.string.link_copy))
                            }
                        },
                        painter = painterResource(id = R.drawable.invite),
                        contentDescription = stringResource(id = R.string.invite)
                    )
                }

            }
        }
        if (contentLoading) {
            ProgressIndicator()
        } else {
            LazyColumn(
                state = scrollState
            ) {
                items(participants) { participant ->
                    ParticipantItem(
                        profile = participant.profileImageUrl,
                        nickname = participant.nickname,
                        team = participant.group,
                        hostNickname = moim.host.nickname,
                        isHostMode = isHostMode,
                        moimId = moim.id,
                        userId = participant.id,
                        isEnd = moim.isEnd,
                        attend = participant.attend,
                        onCameButtonClicked = onCameButtonClicked
                    )
                }
            }
        }

    }
}

@Composable
private fun ParticipantItem(
    profile: String,
    nickname: String,
    team: String,
    hostNickname: String,
    isHostMode: Boolean,
    moimId: Int,
    userId: Int,
    isEnd: Boolean,
    attend: Boolean,
    onCameButtonClicked: (Int, Int, Boolean) -> Unit,
) {
    var isCome by remember { mutableStateOf(attend) }
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
                AsyncImage(
                    modifier = Modifier.size(56.dp),
                    model = profile,
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
                        if (hostNickname == nickname) {
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
            if (isHostMode) {
                Button(
                    modifier = Modifier.height(24.dp),
                    onClick = {
                        isCome = !isCome
                        onCameButtonClicked(moimId, userId, isCome)
                    },
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
                    ),
                    enabled = (hostNickname != nickname) && isEnd.not()
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