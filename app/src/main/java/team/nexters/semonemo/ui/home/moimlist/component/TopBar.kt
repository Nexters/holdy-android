package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.theme.White

@Composable
internal fun TopBar(
    title: String,
    painter: Painter,
    contentDescription: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Image(
            painter = painter,
            contentDescription = contentDescription
        )

    }
}

@Composable
internal fun EndMoimFilter(
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    buttonText: String,
) {
    Row(
        modifier = Modifier.noRippleClickable { onCheckedChanged(checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleCheckbox(
            selected = checked,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = buttonText,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Gray6
            )
        )
    }

}


@Composable
private fun CircleCheckbox(
    selected: Boolean,
) {

    val imageVector = Icons.Filled.CheckCircle
    val tint = if (selected) MaterialTheme.colorScheme.primary else Gray3
    val background = White
    Icon(
        imageVector = imageVector,
        tint = tint,
        modifier = Modifier
            .size(20.dp)
            .background(background, shape = CircleShape),
        contentDescription = stringResource(id = R.string.checkbox)
    )
}
