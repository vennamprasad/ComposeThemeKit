package prasad.vennam.design.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import prasad.vennam.design.theme.LocalHapticEngine

/**
 * A styled Button that automatically integrates with the AuraComposeThemeKit interaction layer.
 *
 * This component handles:
 * 1. **Auto-scaling**: Adapts its [shape] based on the active `ThemeConfig.styleShapeScale`.
 * 2. **Haptics**: Automatically triggers vibrations via [LocalHapticEngine] when pressed.
 *
 * @param onClick Callback when the button is clicked.
 * @param modifier Standard Compose modifier.
 * @param enabled Whether the button is interactable.
 * @param shape The button's structural geometry (pre-scaled by the theme engine).
 * @param colors The color palette for the button states.
 * @param interactionSource Stream of interactions for this button.
 * @param content The composable label/icon for the button.
 */
@Composable
fun ThemeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val hapticEngine = LocalHapticEngine.current
    val isPressed by interactionSource.collectIsPressedAsState()

    // Trigger haptic feedback automatically on press using the user's ThemeConfig intensity
    LaunchedEffect(isPressed) {
        if (isPressed && enabled) {
            hapticEngine.performClick()
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        interactionSource = interactionSource,
        content = {
            content()
        }
    )
}
