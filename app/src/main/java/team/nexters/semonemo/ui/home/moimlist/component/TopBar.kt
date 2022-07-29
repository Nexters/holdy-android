package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.White

@Composable
internal fun TopBar(
    title: String,
    buttonText: String,
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        EndMoimFilter(
            checked = checked,
            onCheckedChanged = onCheckedChanged,
            buttonText = buttonText
        )
    }
}

@Composable
private fun EndMoimFilter(
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
        Text(buttonText)
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
        modifier = Modifier.background(background, shape = CircleShape),
        contentDescription = stringResource(id = R.string.checkbox_content_description)
    )
}
