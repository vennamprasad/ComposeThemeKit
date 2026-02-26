package prasad.vennam.settings.presentation.components

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel

@Composable
fun DisplaySettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel
) {
    val icons = LocalIcons.current

    SettingsGroup(title = "Display") {
        SettingsTile(
            icon = icons.settingsBrightness,
            title = "True Black",
            subtitle = "Use pure black for dark mode (OLED)",
            action = {
                Switch(
                    checked = config.isTrueBlack,
                    enabled = config.isDarkTheme,
                    onCheckedChange = viewModel::setIsTrueBlack
                )
            })

        SettingsTile(
            icon = icons.contrast,
            title = "High Contrast",
            subtitle = "Maximize text legibility",
            action = {
                Switch(
                    checked = config.isHighContrast, onCheckedChange = viewModel::setIsHighContrast
                )
            })

        SettingsTile(
            icon = icons.compress,
            title = "Compact Mode",
            subtitle = "Reduce spacing to fit more content",
            action = {
                Switch(
                    checked = config.isCompactMode, onCheckedChange = viewModel::setIsCompactMode
                )
            })
    }
}
