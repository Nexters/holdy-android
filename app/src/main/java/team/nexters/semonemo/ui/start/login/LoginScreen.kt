package team.nexters.semonemo.ui.start.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.ErrorScreen
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.ui.start.StartEvent
import team.nexters.semonemo.ui.start.StartViewModel

@Composable
internal fun LoginScreen(
    viewModel: StartViewModel,
    navigateToOnBoarding: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    var code by viewModel.code
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                StartEvent.LoginSuccess ->
                    navigateToOnBoarding()
                else -> {

                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.commonErrorChannel.collectWithLifecycle(lifecycleOwner) { message ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = message
            )
        }
    }
    val state = viewModel.loginUiState.collectAsState().value
    Scaffold(
        scaffoldState = scaffoldState,
    ) { contentPadding ->
        contentPadding
        if (state.isNetworkError) {
            ErrorScreen(
                text = stringResource(id = R.string.network_error),
                onclick = { viewModel.refresh() }
            )
        } else {
            LoginScreen(
                onLogin = viewModel::login,
                code = code,
                setCode = { code = it }
            )
        }

    }

}

@Composable
private fun LoginScreen(
    code: String,
    setCode: (String) -> Unit,
    onLogin: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.dp))
        Image(
            painter = painterResource(id = R.drawable.holdy_logo_title),
            contentDescription = stringResource(id = R.string.holdy_logo)
        )
        Spacer(modifier = Modifier.height(157.dp))
        TextField(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .drawColoredShadow(
                    offsetY = (-2).dp
                ),
            value = code,
            onValueChanged = { s ->
                val count = s.count { it == '\n' }
                if (count < 1) {
                    setCode(s)
                }

            },
            placeHolderText = stringResource(id = R.string.placeholder_code)
        )
        Button(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(48.dp),
            onClick = { onLogin(code) },
            shape = RoundedCornerShape(8.dp),
            text = stringResource(id = R.string.start)
        )
        Text(
            modifier = Modifier.noRippleClickable { },
            text = stringResource(id = R.string.no_code),
            style = MaterialTheme.typography.caption.copy(
                color = Gray6
            ),
        )
    }
}