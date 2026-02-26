package prasad.vennam.model

import androidx.compose.runtime.Immutable

/**
 * Central configuration object for the AuraComposeThemeKit engine.
 *
 * This data class stores the user's current aesthetic preferences. Instead of broad enums,
 * it uses ID strings to allow developers to register custom colors, fonts, and styles
 * at runtime without modifying the library's source.
 *
 * @property useDynamicColor Whether to use Material You dynamic colors (Android 12+).
 * @property isDarkTheme Forces dark mode if true, light mode if false.
 * @property isHighContrast Increases contrast levels for accessibility.
 * @property styleShapeScale Global multiplier for corner radii (e.g., 0.5f for sharper, 2.0f for rounder).
 * @property styleSpacingScale Global multiplier for layout spacing and margins.
 * @property styleTextScale Global multiplier for font sizes.
 * @property brandColorId The ID of the primary brand color from [ThemeRegistry].
 * @property fontFamilyId The ID of the font family from [ThemeRegistry].
 * @property uiStyleId The structural style ID (e.g., "rounded", "square").
 * @property isTrueBlack If true, uses pure #000000 surfaces in dark mode (AMOLED).
 * @property isCompactMode Reduces vertical padding for denser layouts.
 * @property animationScale Global duration multiplier for all theme-aware transitions.
 * @property hapticIntensityId The feedback strength ID from [ThemeRegistry].
 * @property elevationStyleId The shadow/elevation rendering style ID.
 * @property appIconId The ID of the home screen icon variant.
 * @property iconStyleId The aesthetic style of internal UI icons.
 */
@Immutable
data class ThemeConfig(
    val useDynamicColor: Boolean = true,
    val isDarkTheme: Boolean = false,
    val isHighContrast: Boolean = false,
    val styleShapeScale: Float = 1.0f,
    val styleSpacingScale: Float = 1.0f,
    val styleTextScale: Float = 1.0f,
    val brandColorId: String = "default",
    val fontFamilyId: String = "default",
    val uiStyleId: String = "rounded",
    val isTrueBlack: Boolean = false,
    val isCompactMode: Boolean = false,
    val animationScale: Float = 1.0f,
    val hapticIntensityId: String = "medium",
    val elevationStyleId: String = "material",
    val appIconId: String = "default",
    val iconStyleId: String = "rounded"
)
