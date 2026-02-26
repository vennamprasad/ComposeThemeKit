package prasad.vennam.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.design.theme.LocalAnimations
import prasad.vennam.design.theme.LocalSemanticColors

/**
 * A circular metric indicator that integrates with AuraComposeThemeKit's semantic palette.
 *
 * This component handles:
 * 1. **Semantic Awareness**: Automatically switches to the "Success" color from [LocalSemanticColors]
 *    when the progress reaches 100%.
 * 2. **Spring Dynamics**: Uses [LocalAnimations.scaledSpring] for smooth, theme-aware transitions.
 *
 * @param progress Current progress value (0.0 to 1.0).
 * @param modifier Standard Compose modifier.
 * @param size Diameter of the circle.
 * @param strokeWidth Thickness of the progress ring.
 * @param primaryColor The color used for active progress (unless 100% complete).
 * @param trackColor The color of the underlying background ring.
 */
@Composable
fun ThemeProgressCircle(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    strokeWidth: Dp = 12.dp,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    val animations = LocalAnimations.current
    val semantics = LocalSemanticColors.current

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = animations.scaledSpring(),
        label = "ProgressAnimation"
    )

    // Dynamic color based on progress completion
    val activeColor = if (progress >= 1.0f) semantics.success else primaryColor

    Box(contentAlignment = Alignment.Center, modifier = modifier.size(size)) {
        Canvas(modifier = Modifier.size(size)) {
            // Background Track
            drawCircle(
                color = trackColor,
                radius = size.toPx() / 2,
                style = Stroke(width = strokeWidth.toPx())
            )

            // Progress Arc
            drawArc(
                color = activeColor,
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        // Percentage Text
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (progress >= 1.0f) semantics.success else MaterialTheme.colorScheme.onSurface
        )
    }
}
