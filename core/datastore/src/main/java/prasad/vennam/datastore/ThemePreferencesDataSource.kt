package prasad.vennam.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import prasad.vennam.model.ThemeConfig
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Suppress("TooManyFunctions")
class ThemePreferencesDataSource @Inject constructor(
    @Named("ThemeDataStore") private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val BRAND_COLOR_ID = stringPreferencesKey("brand_color_id")
        val USE_DYNAMIC_COLOR = booleanPreferencesKey("use_dynamic_color")
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val IS_HIGH_CONTRAST = booleanPreferencesKey("is_high_contrast")
        val STYLE_SHAPE_SCALE = floatPreferencesKey("style_shape_scale")
        val STYLE_SPACING_SCALE = floatPreferencesKey("style_spacing_scale")
        val STYLE_TEXT_SCALE = floatPreferencesKey("style_text_scale")
        val FONT_FAMILY = stringPreferencesKey("font_family")
        val UI_STYLE = stringPreferencesKey("ui_style")
        val IS_TRUE_BLACK = booleanPreferencesKey("is_true_black")
        val IS_COMPACT_MODE = booleanPreferencesKey("is_compact_mode")
        val ANIMATION_SCALE = floatPreferencesKey("animation_scale")
        val HAPTIC_INTENSITY = stringPreferencesKey("haptic_intensity")
        val ELEVATION_STYLE = stringPreferencesKey("elevation_style")
        val APP_ICON = stringPreferencesKey("app_icon")
        val ICON_STYLE = stringPreferencesKey("icon_style")
    }

    val themeConfig: Flow<ThemeConfig> = dataStore.data.map { preferences ->
        ThemeConfig(
            useDynamicColor = preferences[Keys.USE_DYNAMIC_COLOR] ?: true,
            isDarkTheme = preferences[Keys.IS_DARK_THEME] ?: false,
            isHighContrast = preferences[Keys.IS_HIGH_CONTRAST] ?: false,
            styleShapeScale = preferences[Keys.STYLE_SHAPE_SCALE] ?: 1.0f,
            styleSpacingScale = preferences[Keys.STYLE_SPACING_SCALE] ?: 1.0f,
            styleTextScale = preferences[Keys.STYLE_TEXT_SCALE] ?: 1.0f,
            brandColorId = preferences[Keys.BRAND_COLOR_ID] ?: "default",
            fontFamilyId = preferences[Keys.FONT_FAMILY] ?: "default",
            uiStyleId = preferences[Keys.UI_STYLE] ?: "rounded",
            isTrueBlack = preferences[Keys.IS_TRUE_BLACK] ?: false,
            isCompactMode = preferences[Keys.IS_COMPACT_MODE] ?: false,
            animationScale = preferences[Keys.ANIMATION_SCALE] ?: 1.0f,
            hapticIntensityId = preferences[Keys.HAPTIC_INTENSITY] ?: "medium",
            elevationStyleId = preferences[Keys.ELEVATION_STYLE] ?: "material",
            appIconId = preferences[Keys.APP_ICON] ?: "default",
            iconStyleId = preferences[Keys.ICON_STYLE] ?: "rounded"
        )
    }

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
        dataStore.edit { it[Keys.USE_DYNAMIC_COLOR] = useDynamicColor }
    }

    suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { it[Keys.IS_DARK_THEME] = isDarkTheme }
    }
    
    suspend fun setIsHighContrast(isHighContrast: Boolean) {
        dataStore.edit { it[Keys.IS_HIGH_CONTRAST] = isHighContrast }
    }

    suspend fun setStyleShapeScale(scale: Float) {
        dataStore.edit { it[Keys.STYLE_SHAPE_SCALE] = scale }
    }

    suspend fun setStyleSpacingScale(scale: Float) {
        dataStore.edit { it[Keys.STYLE_SPACING_SCALE] = scale }
    }

    suspend fun setStyleTextScale(scale: Float) {
        dataStore.edit { it[Keys.STYLE_TEXT_SCALE] = scale }
    }

    suspend fun setBrandColorId(id: String) {
        dataStore.edit { it[Keys.BRAND_COLOR_ID] = id }
    }

    suspend fun setFontFamilyId(id: String) {
        dataStore.edit { it[Keys.FONT_FAMILY] = id }
    }

    suspend fun setUiStyleId(id: String) {
        dataStore.edit { it[Keys.UI_STYLE] = id }
    }

    suspend fun setIsTrueBlack(isTrueBlack: Boolean) {
        dataStore.edit { it[Keys.IS_TRUE_BLACK] = isTrueBlack }
    }

    suspend fun setIsCompactMode(isCompactMode: Boolean) {
        dataStore.edit { it[Keys.IS_COMPACT_MODE] = isCompactMode }
    }

    suspend fun setAnimationScale(scale: Float) {
        dataStore.edit { it[Keys.ANIMATION_SCALE] = scale }
    }

    suspend fun setHapticIntensityId(id: String) {
        dataStore.edit { it[Keys.HAPTIC_INTENSITY] = id }
    }

    suspend fun setElevationStyleId(id: String) {
        dataStore.edit { it[Keys.ELEVATION_STYLE] = id }
    }

    suspend fun setAppIconId(id: String) {
        dataStore.edit { it[Keys.APP_ICON] = id }
    }

    suspend fun setIconStyleId(id: String) {
        dataStore.edit { it[Keys.ICON_STYLE] = id }
    }

    suspend fun updateConfig(config: ThemeConfig) {
        dataStore.edit { preferences ->
            preferences[Keys.USE_DYNAMIC_COLOR] = config.useDynamicColor
            preferences[Keys.IS_DARK_THEME] = config.isDarkTheme
            preferences[Keys.IS_HIGH_CONTRAST] = config.isHighContrast
            preferences[Keys.STYLE_SHAPE_SCALE] = config.styleShapeScale
            preferences[Keys.STYLE_SPACING_SCALE] = config.styleSpacingScale
            preferences[Keys.STYLE_TEXT_SCALE] = config.styleTextScale
            preferences[Keys.BRAND_COLOR_ID] = config.brandColorId
            preferences[Keys.FONT_FAMILY] = config.fontFamilyId
            preferences[Keys.UI_STYLE] = config.uiStyleId
            preferences[Keys.IS_TRUE_BLACK] = config.isTrueBlack
            preferences[Keys.IS_COMPACT_MODE] = config.isCompactMode
            preferences[Keys.ANIMATION_SCALE] = config.animationScale
            preferences[Keys.HAPTIC_INTENSITY] = config.hapticIntensityId
            preferences[Keys.ELEVATION_STYLE] = config.elevationStyleId
            preferences[Keys.APP_ICON] = config.appIconId
            preferences[Keys.ICON_STYLE] = config.iconStyleId
        }
    }
}
