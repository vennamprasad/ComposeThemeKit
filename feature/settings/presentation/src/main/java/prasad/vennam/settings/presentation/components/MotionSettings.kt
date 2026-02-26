package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel
import prasad.vennam.settings.presentation.ThemeOptions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MotionSettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel,
    options: ThemeOptions
) {
    val dimensions = LocalDimensions.current
    val icons = LocalIcons.current

    SettingsGroup(title = "Motion & Feel") {
        // Animation Scale
        ScaleActionTile(
            icon = icons.formatShapes,
            label = "Animation Speed",
            value = config.animationScale,
            valueLabel = when (config.animationScale) {
                0.5f -> "Brisk"
                1.0f -> "Normal"
                2.0f -> "Relaxed"
                else -> "${config.animationScale}x"
            },
            onValueChange = viewModel::setAnimationScale,
            range = 0.5f..2.0f
        )

        // Haptic Feedback
        Column(
            modifier = Modifier.padding(
                horizontal = dimensions.spacing.medium, vertical = dimensions.spacing.small
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icons.vibration,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(dimensions.iconSize.medium)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.medium))
                Text(
                    text = "Haptic Intensity", style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)) {
                options.availableHapticIntensities.forEach { intensity ->
                    FilterChip(
                        selected = config.hapticIntensityId == intensity.id,
                        onClick = { viewModel.setHapticIntensityId(intensity.id) },
                        label = { Text(intensity.name) })
                }
            }
        }
    }
}
