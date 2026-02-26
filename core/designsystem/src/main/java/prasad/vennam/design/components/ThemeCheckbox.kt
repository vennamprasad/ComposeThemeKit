package prasad.vennam.design.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalHapticEngine

/**
 * A highly reusable Checkbox component that scales automatically with the user's
 * `ThemeConfig` spacing dimensions and applies click haptics.
 */
@Composable
fun ThemeCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val hapticEngine = LocalHapticEngine.current
    val dimensions = LocalDimensions.current

    // Determine a scale relative to the base dimension modifier
    // Baseline spacing is 16.dp for medium. We use a ratio.
    val scalingFactor = (dimensions.spacing.medium.value / 16f).coerceIn(0.5f, 2f)

    Checkbox(
        checked = checked,
        onCheckedChange = { newValue ->
            hapticEngine.performClick()
            if (onCheckedChange != null) {
                onCheckedChange(newValue)
            }
        },
        modifier = modifier.graphicsLayer {
            scaleX = scalingFactor
            scaleY = scalingFactor
        },
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}
