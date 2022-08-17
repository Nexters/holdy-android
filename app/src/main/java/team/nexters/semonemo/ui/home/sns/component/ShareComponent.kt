package team.nexters.semonemo.ui.home.sns

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Black1
import team.nexters.semonemo.theme.Gray5

@Composable
fun StickerContent(
    date: String = "1",
    place: String = "2",
    @DrawableRes hold: Int = R.drawable.hold1,
    captureController: CaptureController,
    onCaputured: (ImageBitmap?, Throwable?) -> Unit
) {
    Capturable(
        controller = captureController,
        onCaptured = onCaputured
    ) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(12.dp),
            color = Black1
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier.size(width = 240.dp, height = 340.dp),
                    painter = painterResource(id = R.drawable.sns_background),
                    contentDescription = stringResource(id = R.string.sns_background)
                )
            }
            Column(
                modifier = Modifier.size(width = 335.dp, 400.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(top = 32.dp),
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