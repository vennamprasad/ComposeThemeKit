package prasad.vennam.design.modifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Helper to draw an Outline directly to a Canvas.
 */
private fun Canvas.drawThemeOutline(outline: Outline, paint: Paint) {
    when (outline) {
        is Outline.Rectangle -> drawRect(outline.rect.left, outline.rect.top, outline.rect.right, outline.rect.bottom, paint)
        is Outline.Rounded -> drawRoundRect(outline.roundRect.left, outline.roundRect.top, outline.roundRect.right, outline.roundRect.bottom, outline.roundRect.topLeftCornerRadius.x, outline.roundRect.topLeftCornerRadius.y, paint)
        is Outline.Generic -> drawPath(outline.path, paint)
    }
}

/**
 * Draws a dual-tone shadow (highlight on top-left, dark shadow on bottom-right)
 * to create an extruded "Soft UI" Neumorphism effect.
 *
 * Note: Uses [drawIntoCanvas] and [Paint.asFrameworkPaint] to utilize legacy shadow layer APIs,
 * since Compose does not yet have a multi-colored shadow native API.
 */
fun Modifier.neumorphicExtruded(
    shape: Shape = RoundedCornerShape(16.dp),
    lightShadowColor: Color = Color.White.copy(alpha = 0.5f),
    darkShadowColor: Color = Color.Black.copy(alpha = 0.15f),
    offset: Dp = 6.dp,
    blurRadius: Dp = 12.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val offsetPx = offset.toPx()
        val blurPx = blurRadius.toPx()
        val outline = shape.createOutline(size, layoutDirection, this)

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
            canvas.drawThemeOutline(outline, paint)

            // 2. Draw dark shadow on the bottom-right
            frameworkPaint.setShadowLayer(
                blurPx,
                offsetPx,
                offsetPx,
                darkShadowColor.toArgb()
            )
            canvas.drawThemeOutline(outline, paint)
        }
    }
)

/**
 * A highly requested tactical modifier that shrinks a button slightly on press and releases
 * with a natural spring animation.
 */
fun Modifier.bounceClickable(
    scaleDown: Float = 0.95f,
    onClick: () -> Unit
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDown else 1f,
        animationSpec = spring(),
        label = "bounceClick"
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    awaitFirstDown(requireUnconsumed = false)
                    isPressed = true
                    waitForUpOrCancellation()
                    isPressed = false
                }
            }
        }
}

/**
 * Sweeping shimmer overlay typically used for loading states.
 */
fun Modifier.shimmerLoading(
    isLoading: Boolean = true,
    shimmerColor: Color = Color.White.copy(alpha = 0.5f),
    backgroundColor: Color = Color.LightGray.copy(alpha = 0.3f)
): Modifier = composed {
    if (!isLoading) return@composed this

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            backgroundColor,
            shimmerColor,
            backgroundColor
        ),
        start = Offset(translateAnim - 200f, translateAnim - 200f),
        end = Offset(translateAnim, translateAnim)
    )

    this.background(brush)
}

/**
 * Applies a premium frosted glass look (semi-transparent fill with a bright subtle inner border).
 * Note: Blurring the layers behind it requires RenderNode manipulations on Android 12+,
 * so this modifier provides the "look" of the glass using border lighting and tinting.
 */
fun Modifier.glassMorphic(
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.White.copy(alpha = 0.15f),
    borderColor: Color = Color.White.copy(alpha = 0.4f),
    borderWidth: Dp = 1.dp
): Modifier = this
    .clip(shape)
    .background(backgroundColor)
    .border(width = borderWidth, color = borderColor, shape = shape)
