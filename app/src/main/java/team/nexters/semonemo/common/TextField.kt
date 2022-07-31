package team.nexters.semonemo.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray5

enum class TexFieldState {
    EMPTY,
    FOCUSING,
    Written
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    placeHolderText: String,
) {
    val focusRequester = remember { FocusRequester() }
    var textFieldState by remember { mutableStateOf(TexFieldState.EMPTY) }
    val color = when (textFieldState) {
        TexFieldState.EMPTY -> Gray3
        TexFieldState.FOCUSING -> MaterialTheme.colors.primary
        TexFieldState.Written -> Gray5
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(BorderStroke(1.dp, color), RoundedCornerShape(8.dp))
            .background(White)
            .noRippleClickable { focusRequester.requestFocus() },
    ) {
        if (value.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = 14.dp,
                        bottom = 14.dp
                    ),
                text = placeHolderText,
                style = MaterialTheme.typography.body2.copy(
                    color = Gray3
                )
            )
        }
        BasicTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(
                    start = 12.dp,
                    top = 14.dp,
                    bottom = 14.dp
                )
                .onFocusChanged { focusState ->
                    textFieldState = if (focusState.isFocused) {
                        TexFieldState.FOCUSING
                    } else if (value.isEmpty()) {
                        TexFieldState.EMPTY
                    } else {
                        TexFieldState.Written
                    }
                },
            value = value,
            textStyle = MaterialTheme.typography.body2,
            onValueChange = onValueChanged,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )

    }
}