package team.nexters.semonemo.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.scale
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.nexters.semonemo.theme.Gray35

internal inline fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    crossinline onClick: () -> Unit
) = composed {
    clickable(
        enabled = enabled,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { onClick() },
    )
}

internal fun Modifier.drawColoredShadow(
    color: Color = Gray35,
    alpha: Float = 0.4f,
    borderRadius: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 2.dp,
    blurRadius: Dp = 4.dp,
    spread: Dp = 0.dp,
    enabled: Boolean = true,
) = if (enabled) {
    this.drawBehind {
        val transparentColor = color.copy(alpha = 0.0f).toArgb()
        val shadowColor = color.copy(alpha = alpha).toArgb()
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparentColor
            frameworkPaint.setShadowLayer(
                blurRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.save()

            if (spread.value > 0) {
                fun calcSpreadScale(spread: Float, childSize: Float): Float {
                    return 1f + ((spread / childSize) * 2f)
                }

                it.scale(
                    calcSpreadScale(spread.toPx(), this.size.width),
                    calcSpreadScale(spread.toPx(), this.size.height),
                    this.center.x,
                    this.center.y
                )
            }

            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
            it.restore()
        }
    }
} else {
    this
}
