package team.nexters.semonemo.ui.home.moimdetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R

@Composable
internal fun InformationContent(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    place: String,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { onBackPressed() },
                painter = painterResource(id = R.drawable.close_white),
                contentDescription = stringResource(id = R.string.close)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h1.copy(
                color = MaterialTheme.colors.onPrimary
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = stringResource(R.string.location),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = place,
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = stringResource(id = R.string.calendar)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
        }
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Row {
            Spacer(
                modifier = Modifier
                    .width(24.dp)
            )
            Text(
                modifier = Modifier.clickable {  },
                text = stringResource(id = R.string.map_open),
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onPrimary.copy(
                        alpha = 0.5f
                    )
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}