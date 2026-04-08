package prasad.vennam.domain

import kotlinx.coroutines.flow.Flow
import prasad.vennam.model.ThemeConfig

/**
 * The Domain-level contract for theme persistence and state management.
 *
 * This repository is the bridge between the UI intents (changing settings)
 * and the low-level data persistence (DataStore).
 */
interface ThemeRepository {
    /**
     * A cold flow of the current [ThemeConfig]. 
     * Subscribers will receive the latest configuration whenever any setting is updated.
     */
    val themeConfig: Flow<ThemeConfig>

    /** Enables or disables Material 3 Dynamic Colors (Android 12+). */
    suspend fun setUseDynamicColor(useDynamicColor: Boolean)

    /** Toggles between Light and Dark themes. */
    suspend fun setIsDarkTheme(isDarkTheme: Boolean)

    /** Toggles High Contrast mode for better accessibility. */
    suspend fun setIsHighContrast(isHighContrast: Boolean)

    /** Updates the global corner radius multiplier. */
    suspend fun setStyleShapeScale(scale: Float)

    /** Updates the global layout spacing multiplier. */
    suspend fun setStyleSpacingScale(scale: Float)

    /** Updates the global typography size multiplier. */
    suspend fun setStyleTextScale(scale: Float)

    /** Selects a brand color from the registry by its unique ID. */
    suspend fun setBrandColorId(id: String)

    /** Selects a font family from the registry by its unique ID. */
    suspend fun setFontFamilyId(id: String)

    /** Sets the structural UI style (Square vs Rounded). */
    suspend fun setUiStyleId(id: String)

    /** Enables OLED-friendly "True Black" surfaces for dark mode. */
    suspend fun setIsTrueBlack(isTrueBlack: Boolean)

    /** Reduces vertical padding for a denser information display. */
    suspend fun setIsCompactMode(isCompactMode: Boolean)

    /** Updates the global duration multiplier for theme animations. */
    suspend fun setAnimationScale(scale: Float)

    /** Updates the intensity level of haptic feedback. */
    suspend fun setHapticIntensityId(id: String)

    /** Selects the shadow/elevation rendering technique. */
    suspend fun setElevationStyleId(id: String)

    /** Updates the application's launcher icon alias name. */
    suspend fun setAppIconId(id: String)

    /** Sets the aesthetic style of internal UI icons (Outlined, Filled, etc). */
    suspend fun setIconStyleId(id: String)

    /** Atomically applies a complete [ThemeConfig] (used for Profiles). */
    suspend fun applyProfile(config: ThemeConfig)

    /** Reverts all settings back to the system defaults. */
    suspend fun resetToDefault()
}
