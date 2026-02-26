package prasad.vennam.design.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import prasad.vennam.design.theme.LocalAnimations

/**
 * A beautiful, animated line graph that adapts to the active theme.
 *
 * This component handles:
 * 1. **Thematic Coloring**: Uses [lineColor] (defaults to Brand Primary) for the stroke and area fill.
 * 2. **Auto-Scaling Animations**: Durations are automatically adjusted via [LocalAnimations].
 * 3. **Dynamic Geometry**: Reacts to the surface background color for point markers.
 *
 * @param dataPoints List of Float values to render.
 * @param modifier Standard Compose modifier.
 * @param lineColor The primary color of the line and gradient area.
 * @param areaAlpha Transparency level for the area under the curve.
 */
@Composable
fun ThemeLineChart(
    dataPoints: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.primary,
    areaAlpha: Float = 0.15f
) {
    if (dataPoints.isEmpty()) return
    
    val animations = LocalAnimations.current
    val animationProgress = remember { Animatable(0f) }

    LaunchedEffect(dataPoints) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = animations.scaledTween(durationMillis = 600)
        )
    }

    val surfaceColor = MaterialTheme.colorScheme.surface

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            val width = size.width
            val height = size.height
            val maxVal = dataPoints.maxOrNull() ?: 1f
            val minVal = dataPoints.minOrNull() ?: 0f
            val range = (maxVal - minVal).coerceAtLeast(1f)
            
            val points = dataPoints.mapIndexed { index, value ->
                val x = (index.toFloat() / (dataPoints.size - 1)) * width
                val y = height - ((value - minVal) / range) * height
                Offset(x, y)
            }

            val strokePath = Path().apply {
                if (points.isNotEmpty()) {
                    moveTo(points[0].x, points[0].y)
                    for (i in 1 until points.size) {
                        // Using linear segments for simplicity, can be cubic if needed
                        lineTo(points[i].x * animationProgress.value, points[i].y)
                    }
                }
            }

            // Draw Area Fill
            val fillPath = Path().apply {
                if (points.isNotEmpty()) {
                    moveTo(points[0].x, height)
                    points.forEach { lineTo(it.x * animationProgress.value, it.y) }
                    lineTo(points.last().x * animationProgress.value, height)
                    close()
                }
            }

            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(lineColor.copy(alpha = areaAlpha), Color.Transparent)
                )
            )

            // Draw Line
            drawPath(
                path = strokePath,
                color = lineColor,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
            
            // Draw Points
            points.forEach { point ->
                drawCircle(
                    color = lineColor,
                    radius = 4.dp.toPx(),
                    center = Offset(point.x * animationProgress.value, point.y)
                )
                drawCircle(
                    color = surfaceColor,
                    radius = 2.dp.toPx(),
                    center = Offset(point.x * animationProgress.value, point.y)
                )
            }
        }
    }
}
