package prasad.vennam.domain

import kotlinx.coroutines.flow.Flow
import prasad.vennam.model.ThemeConfig

interface ThemeRepository {
    val themeConfig: Flow<ThemeConfig>

    suspend fun setUseDynamicColor(useDynamicColor: Boolean)
    suspend fun setIsDarkTheme(isDarkTheme: Boolean)
    suspend fun setIsHighContrast(isHighContrast: Boolean)
    suspend fun setStyleShapeScale(scale: Float)
    suspend fun setStyleSpacingScale(scale: Float)
    suspend fun setStyleTextScale(scale: Float)
    suspend fun setBrandColorId(id: String)
    suspend fun setFontFamilyId(id: String)
    suspend fun setUiStyleId(id: String)
    suspend fun setIsTrueBlack(isTrueBlack: Boolean)
    suspend fun setIsCompactMode(isCompactMode: Boolean)
    suspend fun setAnimationScale(scale: Float)
    suspend fun setHapticIntensityId(id: String)
    suspend fun setElevationStyleId(id: String)
    suspend fun setAppIconId(id: String)
    suspend fun setIconStyleId(id: String)
    suspend fun applyProfile(config: ThemeConfig)
}
