package team.nexters.semonemo.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
internal fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
    shape: Shape,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(
        color = MaterialTheme.colors.onPrimary
    ),
    textColor: Color = Color.Unspecified,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}
