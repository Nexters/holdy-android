package team.nexters.semonemo.ui.home.moimcreate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.common.TexFieldState
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray5


@Composable
internal fun DoubleTextField(
    modifier: Modifier = Modifier,
    firstText: String,
    onFirstTextChanged: (String) -> Unit,
    firstPlaceHolderText: String,
    secondText: String,
    onSecondTextChanged: (String) -> Unit,
    secondPlaceHolderText: String,
) {
    var textFieldState by remember { mutableStateOf(TexFieldState.EMPTY) }
    val color = when (textFieldState) {
        TexFieldState.EMPTY -> Gray3
        TexFieldState.FOCUSING -> MaterialTheme.colorScheme.primary
        TexFieldState.Written -> Gray5
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .border(
                BorderStroke(1.dp, color),
                RoundedCornerShape(8.dp)
            )
            .drawColoredShadow(
                offsetY = 2.dp,
                blurRadius = 4.dp
            )
    ) {
        InnerTextField(
            value = firstText,
            onValueChanged = onFirstTextChanged,
            onSelectChanged = { textFieldState = it },
            placeHolderText = firstPlaceHolderText
        )
        Divider(
            thickness = 1.dp,
            color = color
        )
        InnerTextField(
            value = secondText,
            onValueChanged = onSecondTextChanged,
            onSelectChanged = { textFieldState = it },
            placeHolderText = secondPlaceHolderText
        )
    }
}

@Composable
internal fun TrippleTextField(
    modifier: Modifier = Modifier,
    firstText: String,
    onFirstTextChanged: (String) -> Unit,
    firstPlaceHolderText: String,
    secondText: String,
    onSecondTextChanged: (String) -> Unit,
    secondPlaceHolderText: String,
    thirdText: String,
    onThirdTextChanged: (String) -> Unit,
    thirdPlaceHolderText: String,
) {
    var textFieldState by remember { mutableStateOf(TexFieldState.EMPTY) }
    val color = when (textFieldState) {
        TexFieldState.EMPTY -> Gray3
        TexFieldState.FOCUSING -> MaterialTheme.colorScheme.primary
        TexFieldState.Written -> Gray5
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .border(
                BorderStroke(1.dp, color),
                RoundedCornerShape(8.dp)
            )
            .drawColoredShadow()
    ) {

        InnerTextField(
            value = firstText,
            onValueChanged = onFirstTextChanged,
            onSelectChanged = { textFieldState = it },
            placeHolderText = firstPlaceHolderText
        )
        Divider(
            thickness = 1.dp,
            color = color
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            InnerTextField(
                value = secondText,
                onValueChanged = onSecondTextChanged,
                onSelectChanged = { textFieldState = it },
                placeHolderText = secondPlaceHolderText
            )
            Divider(
                color = color,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(1.dp)
            )
            InnerTextField(
                value = thirdText,
                onValueChanged = onThirdTextChanged,
                onSelectChanged = { textFieldState = it },
                placeHolderText = thirdPlaceHolderText
            )
        }

    }
}

@Composable
private fun ColumnScope.InnerTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    onSelectChanged: (TexFieldState) -> Unit,
    placeHolderText: String
) {
    InnerTextField(
        modifier = Modifier.weight(1f),
        value = value,
        onValueChanged = onValueChanged,
        onSelectChanged = onSelectChanged,
        placeHolderText = placeHolderText
    )
}


@Composable
private fun RowScope.InnerTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    onSelectChanged: (TexFieldState) -> Unit,
    placeHolderText: String
) {
    InnerTextField(
        modifier = Modifier.weight(1f),
        value = value,
        onValueChanged = onValueChanged,
        onSelectChanged = onSelectChanged,
        placeHolderText = placeHolderText
    )
}

@Composable
private fun InnerTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    onSelectChanged: (TexFieldState) -> Unit,
    placeHolderText: String
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
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
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Gray3
                )
            )
        }
        BasicTextField(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    top = 14.dp,
                    bottom = 14.dp
                )
                .onFocusChanged { focusState ->
                    onSelectChanged(
                        if (focusState.isFocused) {
                            TexFieldState.FOCUSING
                        } else if (value.isEmpty()) {
                            TexFieldState.EMPTY
                        } else {
                            TexFieldState.Written
                        }
                    )
                }
                .focusRequester(focusRequester),
            value = value,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = onValueChanged,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

    }
}
