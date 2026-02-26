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

/**
 * Configuration class that allows the host application to customize which
 * theming options are available to the user in the Settings screen.
 */
data class ThemeOptions(
    val availableFonts: List<ThemeFont> = ThemeRegistry.fonts,
    val availableIconStyles: List<ThemeIconStyle> = ThemeRegistry.iconStyles,
    val availableAppIcons: List<ThemeAppIcon> = ThemeRegistry.appIcons,
    val availableBrandColors: List<ThemeColor> = ThemeRegistry.colors,
    val availableUiStyles: List<ThemeUiStyle> = ThemeRegistry.uiStyles,
    val availableHapticIntensities: List<ThemeHapticIntensity> = ThemeRegistry.hapticIntensities,
    val availableElevationStyles: List<ThemeElevationStyle> = ThemeRegistry.elevationStyles,
    val availableProfiles: List<ThemeProfile> = ThemeRegistry.profiles
)
