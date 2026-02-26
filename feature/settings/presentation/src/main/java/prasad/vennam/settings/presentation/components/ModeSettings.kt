package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel

@Composable
fun ModeSettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel
) {
    val dimensions = LocalDimensions.current
    val icons = LocalIcons.current

    SettingsGroup(title = "Mode") {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)
        ) {
            ModeChip(
                selected = !config.isDarkTheme && !config.useDynamicColor, // Simplified logic for demo
                label = "Light", icon = icons.lightMode, onClick = {
                    viewModel.setIsDarkTheme(false)
                    viewModel.setUseDynamicColor(false)
                })
            ModeChip(
                selected = config.isDarkTheme,
                label = "Dark",
                icon = icons.darkMode,
                onClick = { viewModel.setIsDarkTheme(true) })
            ModeChip(
                selected = config.useDynamicColor,
                label = "System",
                icon = icons.autoMode,
                onClick = {
                    viewModel.setUseDynamicColor(true)
                    // System default dark mode logic would ideally be checked here or handled by OS
                })
        }
    }
}
