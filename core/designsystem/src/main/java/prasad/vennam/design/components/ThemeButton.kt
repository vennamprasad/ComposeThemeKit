package prasad.vennam.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import prasad.vennam.design.modifiers.neumorphicExtruded
import prasad.vennam.design.theme.LocalElevations
import prasad.vennam.design.theme.LocalHapticEngine
import prasad.vennam.model.ElevationType

/**
 * A styled Button that automatically integrates with the AuraComposeThemeKit interaction layer.
 *
 * This component handles:
 * 1. **Auto-scaling**: Adapts its [shape] based on the active `ThemeConfig.styleShapeScale`.
 * 2. **Haptics**: Automatically triggers vibrations via [LocalHapticEngine] when pressed.
 * 3. **Neumorphism**: Conditionally flattens extruded shadows on press if enabled.
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
    val elevations = LocalElevations.current
    val isPressed by interactionSource.collectIsPressedAsState()

    // Trigger haptic feedback automatically on press using the user's ThemeConfig intensity
    LaunchedEffect(isPressed) {
        if (isPressed && enabled) {
            hapticEngine.performClick()
        }
    }

    if (elevations.type == ElevationType.NEUMORPHIC_EXTRUDED) {
        val backgroundColor = MaterialTheme.colorScheme.background
        val contentColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        
        // Flatten the shadows when pressed to mimic depth changes
        val clickOffset = if (isPressed) 1.dp else 6.dp
        
        Box(
            modifier = modifier
                .neumorphicExtruded(offset = clickOffset)
                .clip(shape)
                .background(backgroundColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null, // Neumorphism relies on structural shadow shifting, not surface ripples
                    enabled = enabled,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge.copy(color = contentColor)) {
                Row(
                    Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    content()
                }
            }
        }
    } else {
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
}
