package prasad.vennam.design.theme

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * A utility class that generates animation specs scaled by the user's preference.
 * If animationScale is 0, animations are effectively disabled.
 */
@Immutable
class ThemeAnimation(
    val scale: Float = 1.0f
) {
    /**
     * Returns a tween spec with the duration scaled by the user's preference.
     */
    fun <T> scaledTween(
        durationMillis: Int = 300,
        delayMillis: Int = 0
    ): TweenSpec<T> {
        val scaledDuration = (durationMillis * scale).toInt()
        val scaledDelay = (delayMillis * scale).toInt()
        return tween(durationMillis = scaledDuration, delayMillis = scaledDelay)
    }

    /**
     * Returns a spring spec. Note: Spring stiffness is usually what's adjusted for "feel",
     * but we can scale the overall responsiveness here if needed.
     */
    fun <T> scaledSpring(
        dampingRatio: Float = Spring.DampingRatioNoBouncy,
        stiffness: Float = Spring.StiffnessMedium
    ): SpringSpec<T> {
        // We can optionally scale stiffness, where lower scale = lower stiffness (slower)
        val scaledStiffness = stiffness * (if (scale > 0) 1f / scale else 1000f)
        return spring(dampingRatio = dampingRatio, stiffness = scaledStiffness)
    }
}

val LocalAnimations = staticCompositionLocalOf { ThemeAnimation() }
