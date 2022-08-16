package team.nexters.semonemo.ui.home.moimcreate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.extension.showDatePicker
import team.nexters.semonemo.extension.showTimePicker
import team.nexters.semonemo.theme.Danger1
import team.nexters.semonemo.theme.Tertiary
import team.nexters.semonemo.ui.home.moimcreate.component.DateTextField
import team.nexters.semonemo.ui.home.moimcreate.component.DoubleTextField

@Composable
internal fun MoimCreateScreen(
    viewModel: MoimCreateViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    val (date, setDate) = remember { mutableStateOf("") }
    val (startTime, setStartTime) = remember { mutableStateOf("") }
    val (endTime, setEndTime) = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                MoimCreateEvent.NavigateToMoimList -> {
                    onBackPressed()
                }
                MoimCreateEvent.OpenDatePicker -> {
                    context.showDatePicker { setDate(it) }
                }
                MoimCreateEvent.OpenStartTimePicker -> {
                    context.showTimePicker { setStartTime(it) }
                }
                MoimCreateEvent.OpenEndTimePicker -> {
                    context.showTimePicker { setEndTime(it) }
                }
                is MoimCreateEvent.CreationFailed -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.result,
                    )
                }
                is MoimCreateEvent.CopyClipBoard -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.result
                    )
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.commonErrorChannel.collectWithLifecycle(lifecycleOwner) { message ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
            )
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
    ) { contentPadding ->
        contentPadding
        MoimCreateScreen(
            date = date,
            startTime = startTime,
            endTime = endTime,
            onCloseButtonClicked = { viewModel.postEvent(MoimCreateEvent.NavigateToMoimList) },
            onDateFocused = { viewModel.postEvent(MoimCreateEvent.OpenDatePicker) },
            onCreateButtonClicked = viewModel::onCreateButtonClicked,
            onStartTimeFocused = { viewModel.postEvent(MoimCreateEvent.OpenStartTimePicker) },
            onEndTimeFocused = { viewModel.postEvent(MoimCreateEvent.OpenEndTimePicker) },
            onClipBoardCopy = { viewModel.postEvent(MoimCreateEvent.CopyClipBoard(it)) }
        )
    }
}

@Composable
private fun MoimCreateScreen(
    date: String,
    startTime: String,
    endTime: String,
    onCloseButtonClicked: () -> Unit,
    onDateFocused: () -> Unit,
    onStartTimeFocused: () -> Unit,
    onEndTimeFocused: () -> Unit,
    onCreateButtonClicked: (String, String, String, String, String) -> Unit,
    onClipBoardCopy: (String) -> Unit
) {

    var address by remember { mutableStateOf("") }
    var detailAddress by remember { mutableStateOf("") }
    var placeLink by remember { mutableStateOf("") }

    handleClipboard(
        setAddress = { address = it },
        setDetailAddress = { detailAddress = it },
        setPlaceLink = { placeLink = it },
        onClipBoardCopy = onClipBoardCopy
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.White),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = Modifier
                    .align(Alignment.End)
                    .noRippleClickable { onCloseButtonClicked() },
                painter = painterResource(id = R.drawable.close),
                contentDescription = stringResource(id = R.string.close)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_date))
                    withStyle(
                        SpanStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        append(stringResource(id = R.string.star))
                    }

                },
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            DateTextField(
                firstText = date,
                onFirstFocused = onDateFocused,
                firstPlaceHolderText = stringResource(id = R.string.placeholder_date),
                secondText = startTime,
                onSecondFocused = onStartTimeFocused,
                secondPlaceHolderText = stringResource(id = R.string.placeholder_start_time),
                thirdText = endTime,
                onThirdFocused = onEndTimeFocused,
                thirdPlaceHolderText = stringResource(id = R.string.placeholder_end_time),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_place))
                    withStyle(
                        SpanStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        append(stringResource(id = R.string.star))
                    }
                },
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            DoubleTextField(
                firstText = address,
                onFirstTextChanged = { address = it },
                firstPlaceHolderText = stringResource(id = R.string.placeholder_address),
                secondText = detailAddress,
                onSecondTextChanged = { detailAddress = it },
                secondPlaceHolderText = stringResource(id = R.string.placeholder_detail_address),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.title_place_link),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.drawColoredShadow(),
                value = placeLink,
                onValueChanged = { placeLink = it },
                placeHolderText = stringResource(id = R.string.placeholder_address_link)
            )
            Spacer(modifier = Modifier.height(12.dp))
            if(placeLink.isNotEmpty()){
                Text(
                    text = stringResource(id = R.string.link_is_correct),
                    style = MaterialTheme.typography.caption.copy(color = Danger1)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    onCreateButtonClicked(
                        parseDate(date, startTime),
                        parseDate(date, endTime),
                        address,
                        detailAddress,
                        placeLink
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = Tertiary,
                    backgroundColor = MaterialTheme.colors.primary
                ),
                enabled = isMoimCreateAvailable(date, startTime, endTime, address, detailAddress),
                shape = RoundedCornerShape(8.dp),
                text = stringResource(id = R.string.moim_create),
            )
        }
    }
}

@Composable
private fun handleClipboard(
    setAddress: (String) -> Unit,
    setDetailAddress: (String) -> Unit,
    setPlaceLink: (String) -> Unit,
    onClipBoardCopy: (String) -> Unit,
) {
    LocalClipboardManager.current.getText()?.text?.let {
        when {
            it.startsWith("[네이버 지도]") -> {
                // ex)
                // [네이버 지도]
                // 더클라임 클라이밍 짐앤샵 양재점
                // 서울 강남구 남부순환로 2615
                // http://naver.me/Fcje76Jl
                val lines = it.split("\n")
                if (lines.size == 4) {
                    setAddress(lines[1])
                    setDetailAddress(lines[2])
                    setPlaceLink(lines[3])
                }
                onClipBoardCopy(stringResource(id = R.string.copy_clipboard))
            }
            //
            it.startsWith("[카카오맵]") -> {
                // ex)
                // [카카오맵] 더클라임짐 연남점
                // 서울 마포구 양화로 186 3층 (동교동)
                //
                // http://kko.to/Y7FWstNi3
                val lines = it.split("\n")
                if (lines.size == 4) {
                    setAddress(lines[0].removePrefix("[카카오맵] "))
                    setDetailAddress(lines[1])
                    setPlaceLink(lines[3])
                }
                onClipBoardCopy(stringResource(id = R.string.copy_clipboard))
            }
        }
    }
}

