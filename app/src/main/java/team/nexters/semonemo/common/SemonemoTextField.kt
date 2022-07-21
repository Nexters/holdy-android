package team.nexters.semonemo.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Green800
import team.nexters.semonemo.theme.Grey200

object SemonemoTextFieldDefaults {
    @Composable
    fun colors() = TextFieldDefaults.textFieldColors(
        containerColor = White,
        cursorColor = Green800,
        focusedIndicatorColor = Green800,
        unfocusedIndicatorColor = Grey200
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SemonemoTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        value = value, onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 14.sp),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.code_placeholder),
                style = TextStyle(fontSize = 14.sp, color = Grey200),
                overflow = TextOverflow.Visible
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        maxLines = 1,
        colors = SemonemoTextFieldDefaults.colors()
    )
}
