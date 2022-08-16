package team.nexters.semonemo.ui.home.hold.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.semonemo.R
import team.nexters.semonemo.extension.noRippleClickable

@Composable
internal fun InformationContent(
    modifier: Modifier = Modifier,
    nickname: String,
    club: String,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .noRippleClickable { onBackPressed() },
                painter = painterResource(id = R.drawable.close_white),
                contentDescription = stringResource(id = R.string.close)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(id = R.drawable.holdy3),
            contentDescription = stringResource(id = R.string.holdy)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = nickname,
            style = MaterialTheme.typography.h3.copy(
                color = MaterialTheme.colors.onPrimary,
                lineHeight = (28.64).sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = club,
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onPrimary,
                lineHeight = (16.71).sp
            )
        )

    }
}