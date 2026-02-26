package prasad.vennam.design.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import prasad.vennam.design.theme.LocalHapticEngine

/**
 * A highly reusable Switch component that automatically applies haptic feedback
 * when the user toggles it, based on the `ThemeConfig` intensity.
 */
@Composable
fun ThemeSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val hapticEngine = LocalHapticEngine.current

    Switch(
        checked = checked,
        onCheckedChange = { newValue ->
            hapticEngine.performClick()
            if (onCheckedChange != null) {
                onCheckedChange(newValue)
            }
        },
        modifier = modifier,
        thumbContent = thumbContent,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}
