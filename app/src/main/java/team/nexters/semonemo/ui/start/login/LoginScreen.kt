package team.nexters.semonemo.ui.start.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.semonemo.R
import team.nexters.semonemo.common.SemonemoButton
import team.nexters.semonemo.common.SemonemoTextField
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.extension.toast
import team.nexters.semonemo.theme.Grey200
import team.nexters.semonemo.ui.start.StartActivity

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                LoginEvent.Success ->
                    (context as StartActivity).startMain()
                LoginEvent.Failed ->
                    toast(context, context.getString(R.string.code_error))
            }
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
        SemonemoTextField(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .drawColoredShadow(
                    offsetY = (-2).dp
                ),
            value = code,
            onValueChange = { s ->
                val count = s.count { it == '\n' }
                if (count < 1) {
                    code = s
                }

            }
        )
        SemonemoButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(48.dp),
            onClick = { onLogin(code) },
            text = stringResource(id = R.string.start)
        )
        Text(
            modifier = Modifier.noRippleClickable { },
            text = stringResource(id = R.string.no_code),
            style = TextStyle(fontSize = 12.sp, color = Grey200),
        )
    }
}
