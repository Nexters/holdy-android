package team.nexters.semonemo.ui.home.moimlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Gray3
import team.nexters.semonemo.theme.Gray6
import team.nexters.semonemo.theme.White

@Composable
internal fun TopBar(
    navigateToHold: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .width(90.dp)
                .height(32.dp),
            painter = painterResource(id = R.drawable.holdy_logo_title),
            contentDescription = stringResource(id = R.string.holdy_logo)
        )
        Image(
            modifier = Modifier.noRippleClickable { navigateToHold() },
            painter = painterResource(id = R.drawable.small_holdy1),
            contentDescription = stringResource(id = R.string.holdy)
        )

    }
}

@Composable
internal fun EndMoimFilter(
    modifier: Modifier = Modifier,
    checked: Boolean,
    buttonText: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleCheckbox(
            selected = checked,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = buttonText,
            style = MaterialTheme.typography.caption.copy(
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
    val tint = if (selected) MaterialTheme.colors.primary else Gray3
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
