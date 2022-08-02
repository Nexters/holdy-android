package team.nexters.semonemo.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.github.skgmn.composetooltip.Tooltip
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.rememberTooltipStyle

@Composable
internal fun Tooltip(
    text: String,
    maxLines: Int,
    anchorEdge: AnchorEdge
) {
    Tooltip(
        tooltipStyle = rememberTooltipStyle(
            color = MaterialTheme.colors.primary,
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 4.5.dp,
                bottom = 5.5.dp,
            ),
            cornerRadius = 4.dp,
            tipWidth = 6.dp,
            tipHeight = 8.dp
        ),
        anchorEdge = anchorEdge
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            maxLines = maxLines
        )
    }
}
