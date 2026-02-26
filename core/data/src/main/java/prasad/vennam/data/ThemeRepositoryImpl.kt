package prasad.vennam.data

import kotlinx.coroutines.flow.Flow
import prasad.vennam.datastore.ThemePreferencesDataSource
import prasad.vennam.domain.ThemeRepository
import prasad.vennam.model.ThemeConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("TooManyFunctions")
class ThemeRepositoryImpl @Inject constructor(
    private val themePreferencesDataSource: ThemePreferencesDataSource
) : ThemeRepository {

    override val themeConfig: Flow<ThemeConfig> = themePreferencesDataSource.themeConfig

    override suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
        themePreferencesDataSource.setUseDynamicColor(useDynamicColor)
    }

    override suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        themePreferencesDataSource.setIsDarkTheme(isDarkTheme)
    }

    override suspend fun setIsHighContrast(isHighContrast: Boolean) {
        themePreferencesDataSource.setIsHighContrast(isHighContrast)
    }

    override suspend fun setStyleShapeScale(scale: Float) {
        themePreferencesDataSource.setStyleShapeScale(scale)
    }

    override suspend fun setStyleSpacingScale(scale: Float) {
        themePreferencesDataSource.setStyleSpacingScale(scale)
    }

    override suspend fun setStyleTextScale(scale: Float) {
        themePreferencesDataSource.setStyleTextScale(scale)
    }

    override suspend fun setBrandColorId(id: String) {
        themePreferencesDataSource.setBrandColorId(id)
    }
    
    override suspend fun setFontFamilyId(id: String) {
        themePreferencesDataSource.setFontFamilyId(id)
    }

    override suspend fun setUiStyleId(id: String) {
        themePreferencesDataSource.setUiStyleId(id)
    }

    override suspend fun setIsTrueBlack(isTrueBlack: Boolean) {
        themePreferencesDataSource.setIsTrueBlack(isTrueBlack)
    }

    override suspend fun setIsCompactMode(isCompactMode: Boolean) {
        themePreferencesDataSource.setIsCompactMode(isCompactMode)
    }

    override suspend fun setAnimationScale(scale: Float) {
        themePreferencesDataSource.setAnimationScale(scale)
    }

    override suspend fun setHapticIntensityId(id: String) {
        themePreferencesDataSource.setHapticIntensityId(id)
    }

    override suspend fun setElevationStyleId(id: String) {
        themePreferencesDataSource.setElevationStyleId(id)
    }

    override suspend fun setAppIconId(id: String) {
        themePreferencesDataSource.setAppIconId(id)
    }

    override suspend fun setIconStyleId(id: String) {
        themePreferencesDataSource.setIconStyleId(id)
    }

    override suspend fun applyProfile(config: ThemeConfig) {
        themePreferencesDataSource.updateConfig(config)
    }
}
