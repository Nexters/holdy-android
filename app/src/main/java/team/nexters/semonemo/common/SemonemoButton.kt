package team.nexters.semonemo.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.theme.Green800

object SemonemoButtonDefaults {
    @Composable
    fun colors() = ButtonDefaults.buttonColors(
        containerColor = Green800
    )
}

@Composable
internal fun SemonemoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors = SemonemoButtonDefaults.colors(),
    text: String
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = colors
    ) {
        Text(
            text = text,
            //   style =
        )
    }
}
