package team.nexters.semonemo.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Gray5
import team.nexters.semonemo.theme.Gray6

@Composable
internal fun EmptyScreen(
    text: String,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lying_holdy),
            contentDescription = stringResource(id = R.string.lying_holdy)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Normal,
                color = Gray5
            ),
            textAlign = TextAlign.Center
        )
        content()
    }
}

@Composable
internal fun ErrorScreen(
    text: String,
    onclick: () -> Unit
) {
    EmptyScreen(text = text) {
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .width(120.dp)
                .height(40.dp),
            onClick = { onclick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background
            ),
            shape = RoundedCornerShape(100.dp),
            text = stringResource(id = R.string.retry),
            textStyle = MaterialTheme.typography.body2.copy(
                color = Gray6
            )
        )
    }
}