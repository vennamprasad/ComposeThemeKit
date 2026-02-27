package prasad.vennam.design.modifiers

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a dual-tone shadow (highlight on top-left, dark shadow on bottom-right)
 * to create an extruded "Soft UI" Neumorphism effect.
 *
 * Note: Uses [drawIntoCanvas] and [Paint.asFrameworkPaint] to utilize legacy shadow layer APIs,
 * since Compose does not yet have a multi-colored shadow native API.
 */
fun Modifier.neumorphicExtruded(
    lightShadowColor: Color = Color.White.copy(alpha = 0.5f),
    darkShadowColor: Color = Color.Black.copy(alpha = 0.15f),
    cornerRadius: Dp = 16.dp,
    offset: Dp = 6.dp,
    blurRadius: Dp = 12.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val radiusPx = cornerRadius.toPx()
        val offsetPx = offset.toPx()
        val blurPx = blurRadius.toPx()

        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            
            // 1. Draw light shadow (highlight) on the top-left
            frameworkPaint.color = Color.Transparent.toArgb()
            frameworkPaint.setShadowLayer(
                blurPx,
                -offsetPx,
                -offsetPx,
                lightShadowColor.toArgb()
            )
            canvas.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = radiusPx,
                radiusY = radiusPx,
                paint = paint
            )

            // 2. Draw dark shadow on the bottom-right
            frameworkPaint.setShadowLayer(
                blurPx,
                offsetPx,
                offsetPx,
                darkShadowColor.toArgb()
            )
            canvas.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = radiusPx,
                radiusY = radiusPx,
                paint = paint
            )
        }
    }
)
