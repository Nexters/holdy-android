package team.nexters.semonemo.ui.start.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.extension.basicExceptionHandler
import team.nexters.semonemo.extension.collectWithLifecycle
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.ui.start.LoginActivity

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as LoginActivity)
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectWithLifecycle(lifecycleOwner) { event ->
            when (event) {
                LoginEvent.Success ->
                    activity.startMain()
                LoginEvent.Failed ->
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.code_error),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.exceptionFlow.collectWithLifecycle(lifecycleOwner) {
            activity.basicExceptionHandler(it)
        }
    }
    LoginScreen(onLogin = viewModel::login)
}

@Composable
private fun LoginScreen(
    onLogin: (String) -> Unit
) {
    var code by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.dp))
        Text("logo") //TODO
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
                    code = s
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
