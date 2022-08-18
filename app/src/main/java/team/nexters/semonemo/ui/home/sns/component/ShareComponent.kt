package team.nexters.semonemo.ui.home.sns.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Black1
import team.nexters.semonemo.theme.Gray5

@Composable
fun StickerContent(
    date: String,
    place: String,
    @DrawableRes hold: Int,
    captureController: CaptureController,
    onCaptured: (ImageBitmap?, Throwable?) -> Unit
) {
    Capturable(
        controller = captureController,
        onCaptured = onCaptured
    ) {
        Column(
            modifier = Modifier
                .size(width = 335.dp, 400.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(12.dp),
                color = Black1,
                elevation = 4.dp
            ) {
                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.small_sns_background),
                        contentDescription = stringResource(id = R.string.sns_background)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .size(
                                width = 85.dp,
                                height = 30.dp
                            ),
                        painter = painterResource(id = R.drawable.holdy_logo),
                        contentDescription = stringResource(id = R.string.holdy_logo)
                    )
                    Spacer(modifier = Modifier.height(56.dp))
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = hold),
                        contentDescription = stringResource(id = R.string.hold)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = place,
                        style = MaterialTheme.typography.h3.copy(
                            color = MaterialTheme.colors.onPrimary
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 48.dp),
                        text = date,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            color = Gray5
                        ),
                    )
                }
            }
        }
    }
}