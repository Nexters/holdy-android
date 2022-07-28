package team.nexters.semonemo.common

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
internal fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = shape,
        colors = colors
    ) {
        Text(
            text = text,
            //   style =
        )
    }
}
