package prasad.vennam.model.registry

import prasad.vennam.model.ThemeAppIcon
import prasad.vennam.model.ThemeColor
import prasad.vennam.model.ThemeElevationStyle
import prasad.vennam.model.ThemeFont
import prasad.vennam.model.ThemeHapticIntensity
import prasad.vennam.model.ThemeIconStyle
import prasad.vennam.model.ThemeProfile
import prasad.vennam.model.ThemeUiStyle
import prasad.vennam.model.ThemeConfig

/**
 * The central hub for all aesthetic options available in the AuraComposeThemeKit system.
 *
 * Developers should use this registry to inject their custom branding (fonts, colors, icons)
 * before the UI is rendered. The Settings screen automatically reads from these lists
 * to populate its selection chips and grids.
 *
 * Usage:
 * ```
 * ThemeRegistry.colors.add(ThemeColor(id = "brand", name = "My Brand", colorValue = 0xFF...))
 * ```
 */
class ThemeRegistry {
    /** Registered font families available for selection. */
    val fonts = mutableListOf<ThemeFont>()

    /** Registered brand colors available for selection. */
    val colors = mutableListOf<ThemeColor>()

    /** aesthetic styles for UI icons (e.g., Rounded, Outlined). */
    val iconStyles = mutableListOf<ThemeIconStyle>()

    /** Home screen icon variants. */
    val appIcons = mutableListOf<ThemeAppIcon>()

    /** Structural UI styles (e.g., Corner radius presets). */
    val uiStyles = mutableListOf<ThemeUiStyle>()

    /** Haptic feedback intensity presets. */
    val hapticIntensities = mutableListOf<ThemeHapticIntensity>()

    /** Shadow/Elevation rendering presets. */
    val elevationStyles = mutableListOf<ThemeElevationStyle>()

    /** Curated theme profiles that apply multiple settings at once. */
    val profiles = mutableListOf<ThemeProfile>()

    init {
        fonts.add(ThemeFont("default", "System Default", isSystemDefault = true))
        fonts.add(ThemeFont("poppins", "Poppins"))
        fonts.add(ThemeFont("nunito", "Nunito"))
        fonts.add(ThemeFont("raleway", "Raleway"))

        colors.add(ThemeColor("default", "Dynamic / Wallpaper", null))
        colors.add(ThemeColor("sapphire", "Sapphire Blue", 0xFF0F52BA))
        colors.add(ThemeColor("emerald", "Emerald Green", 0xFF50C878))
        colors.add(ThemeColor("purple", "Royal Purple", 0xFF7851A9))
        colors.add(ThemeColor("orange", "Sunset Orange", 0xFFFF8C00))
        colors.add(ThemeColor("crimson", "Crimson Red", 0xFFDC143C))
        colors.add(ThemeColor("navy", "Midnight Navy", 0xFF000080))
        colors.add(ThemeColor("magenta", "Vibrant Magenta", 0xFFFF00FF))
        colors.add(ThemeColor("goldenrod", "Goldenrod", 0xFFDAA520))
        colors.add(ThemeColor("neon_yellow", "Cyber Yellow", 0xFFF3F200))
        colors.add(ThemeColor("cyber_cyan", "Cyber Cyan", 0xFF00F0FF))
        colors.add(ThemeColor("forest_green", "Forest Green", 0xFF1B4D3E))
        colors.add(ThemeColor("retro_amber", "Retro Amber", 0xFFFFBF00))

        iconStyles.add(ThemeIconStyle("rounded", "Rounded"))
        iconStyles.add(ThemeIconStyle("filled", "Filled"))
        iconStyles.add(ThemeIconStyle("outlined", "Outlined"))
        iconStyles.add(ThemeIconStyle("sharp", "Sharp"))
        
        appIcons.add(ThemeAppIcon("default", "Default", "MainActivityDefault"))
        appIcons.add(ThemeAppIcon("dark", "Dark", "MainActivityDark"))
        
        uiStyles.add(ThemeUiStyle("rounded", "Rounded"))
        uiStyles.add(ThemeUiStyle("square", "Square"))
        uiStyles.add(ThemeUiStyle("extra_rounded", "Extra Rounded"))
        
        hapticIntensities.add(ThemeHapticIntensity("none", "None"))
        hapticIntensities.add(ThemeHapticIntensity("light", "Light"))
        hapticIntensities.add(ThemeHapticIntensity("medium", "Medium"))
        hapticIntensities.add(ThemeHapticIntensity("heavy", "Heavy"))
        
        elevationStyles.add(ThemeElevationStyle("flat", "Flat"))
        elevationStyles.add(ThemeElevationStyle("material", "Material"))
        elevationStyles.add(ThemeElevationStyle("material", "Material"))
        elevationStyles.add(ThemeElevationStyle("high_contrast", "High Contrast"))

        profiles.add(
            ThemeProfile(
                id = "sunset_orange",
                name = "Sunset Orange",
                config = ThemeConfig(
                    isDarkTheme = false,
                    brandColorId = "goldenrod",
                    uiStyleId = "square",
                    hapticIntensityId = "light",
                    elevationStyleId = "high_contrast"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "emerald_nature",
                name = "Emerald Nature",
                config = ThemeConfig(
                    isDarkTheme = false,
                    brandColorId = "emerald",
                    fontFamilyId = "nunito",
                    uiStyleId = "extra_rounded"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "royal_purple",
                name = "Royal Purple",
                config = ThemeConfig(
                    isDarkTheme = true,
                    brandColorId = "purple",
                    fontFamilyId = "poppins",
                    uiStyleId = "rounded"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "midnight_sapphire",
                name = "Midnight Sapphire",
                config = ThemeConfig(
                    isDarkTheme = true,
                    brandColorId = "sapphire",
                    fontFamilyId = "raleway",
                    uiStyleId = "extra_rounded",
                    elevationStyleId = "material"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "industrial_slate",
                name = "Industrial Slate",
                config = ThemeConfig(
                    isDarkTheme = false,
                    brandColorId = "navy",
                    fontFamilyId = "default",
                    uiStyleId = "square",
                    elevationStyleId = "flat"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "crimson_stealth",
                name = "Crimson Stealth",
                config = ThemeConfig(
                    isDarkTheme = true,
                    isTrueBlack = true,
                    brandColorId = "crimson",
                    fontFamilyId = "poppins",
                    uiStyleId = "rounded",
                    elevationStyleId = "high_contrast"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "cyberpunk",
                name = "Cyberpunk 2077",
                config = ThemeConfig(
                    isDarkTheme = true,
                    isTrueBlack = true,
                    brandColorId = "neon_yellow",
                    fontFamilyId = "poppins",
                    uiStyleId = "square",
                    hapticIntensityId = "heavy",
                    elevationStyleId = "high_contrast"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "nordic_forest",
                name = "Nordic Forest",
                config = ThemeConfig(
                    isDarkTheme = false,
                    brandColorId = "forest_green",
                    fontFamilyId = "nunito",
                    uiStyleId = "extra_rounded",
                    hapticIntensityId = "light",
                    elevationStyleId = "flat"
                )
            )
        )
        profiles.add(
            ThemeProfile(
                id = "retro_terminal",
                name = "Retro Terminal",
                config = ThemeConfig(
                    isDarkTheme = true,
                    isTrueBlack = true,
                    brandColorId = "retro_amber",
                    fontFamilyId = "default",
                    uiStyleId = "square",
                    hapticIntensityId = "medium",
                    elevationStyleId = "flat"
                )
            )
        )
    }
}
