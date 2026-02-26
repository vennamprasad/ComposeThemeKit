package prasad.vennam.settings.presentation

import prasad.vennam.model.ThemeAppIcon
import prasad.vennam.model.ThemeColor
import prasad.vennam.model.ThemeElevationStyle
import prasad.vennam.model.ThemeFont
import prasad.vennam.model.ThemeHapticIntensity
import prasad.vennam.model.ThemeIconStyle
import prasad.vennam.model.ThemeProfile
import prasad.vennam.model.ThemeUiStyle
import prasad.vennam.model.registry.ThemeRegistry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import prasad.vennam.design.theme.LocalThemeRegistry

@Composable
fun rememberThemeOptions(registry: ThemeRegistry = LocalThemeRegistry.current): ThemeOptions {
    return remember(registry) {
        ThemeOptions(
            availableFonts = registry.fonts,
            availableIconStyles = registry.iconStyles,
            availableAppIcons = registry.appIcons,
            availableBrandColors = registry.colors,
            availableUiStyles = registry.uiStyles,
            availableHapticIntensities = registry.hapticIntensities,
            availableElevationStyles = registry.elevationStyles,
            availableProfiles = registry.profiles
        )
    }
}

/**
 * Configuration class that allows the host application to customize which
 * theming options are available to the user in the Settings screen.
 */
data class ThemeOptions(
    val availableFonts: List<ThemeFont>,
    val availableIconStyles: List<ThemeIconStyle>,
    val availableAppIcons: List<ThemeAppIcon>,
    val availableBrandColors: List<ThemeColor>,
    val availableUiStyles: List<ThemeUiStyle>,
    val availableHapticIntensities: List<ThemeHapticIntensity>,
    val availableElevationStyles: List<ThemeElevationStyle>,
    val availableProfiles: List<ThemeProfile>
)
